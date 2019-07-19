package com.material.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.material.service.SecurityService;
import com.material.types.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class userMutation implements GraphQLMutationResolver {
    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private SecurityService securityService;


    public Boolean addUser(User user){
        if(securityService.ifLogin() == false){
            return false;
        }
        if(user.getId().equals("")){
            return false;
        }
        Query query = new Query(Criteria.where("id").is(user.getId()));
        if(mongoTemplate.findOne(query, User.class)!=null){
            return false;
        }
        mongoTemplate.save(user);
        return true;
    }

    public Boolean updateUser(User user){
        if(securityService.ifLogin() == false){
            return false;
        }
        if(user.getId().equals("")){
            return false;
        }
        Query query = new Query(Criteria.where("id").is(user.getId()));
        if(mongoTemplate.findOne(query, User.class)==null){
            return false;
        }
        Update update = new Update();
        update.set("passwd",user.getPasswd());
        mongoTemplate.updateFirst(query,update,User.class);
        return true;
    }

    public Boolean deleteUser(String id){
        if(securityService.ifLogin() == false){
            return false;
        }
        Query query = new Query(Criteria.where("id").is(id ));
        DeleteResult deleteResult = mongoTemplate.remove(query,User.class);
        return (deleteResult.getDeletedCount()>0);
    }
}
