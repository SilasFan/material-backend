package com.material.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.material.service.SecurityService;
import com.material.types.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Component
public class goodMutation implements GraphQLMutationResolver {
    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private SecurityService securityService;

    public Good addGood(Good good){
        if(securityService.ifLogin() == false){
            return new Good("未登录");
        }
        Query query = new Query(Criteria.where("id").exists(true));
        query.with(new Sort(Sort.Direction.DESC,"id"));
        query.limit(1);
        Integer last = mongoTemplate.findOne(query,Good.class).getId();
        good.setId(last+1);
        good.setBorrow(0);
        good.setBrokenDes(new ArrayList<>());
        mongoTemplate.save(good);
        return good;
    }

    public Boolean updateGood(Good good){
        if(securityService.ifLogin() == false){
            return false;
        }
        Query query = new Query(Criteria.where("id").is(good.getId()));
        Good qGood = mongoTemplate.findOne(query,Good.class);

        if(qGood==null){
            return false;
        }

        if(good.getAmount()<good.getBorrow()){
            return false;
        }

        good.setBrokenDes(qGood.getBrokenDes());
        mongoTemplate.save(good);
        return true;
    }

    public Boolean deleteGood(Integer id){
        if(securityService.ifLogin() == false){
            return false;
        }
        Query query = new Query(Criteria.where("id").is(id));
        Good good = mongoTemplate.findOne(query,Good.class);
        if(good==null){
            return false;
        }
        mongoTemplate.remove(good);
        return true;
    }
}
