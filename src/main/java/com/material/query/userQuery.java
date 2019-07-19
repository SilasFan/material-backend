package com.material.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.material.jwt.JwtUtil;
import com.material.types.LoginMsg;
import com.material.types.User;
import graphql.GraphQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component

public class userQuery implements GraphQLQueryResolver {

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private JwtUtil jwtUtil;



    public List<User> allUser() {
        return mongoTemplate.findAll(User.class);
    }

    public LoginMsg login( String id, String passwd) throws GraphQLException{
        System.out.println(jwtUtil.getKey());

        Query query = new Query(Criteria.where("id").is(id));

        User real = mongoTemplate.findOne(query, User.class);

        if(real == null){

            return new LoginMsg(null,"帐号不存在");
        }

        System.out.println(real.getId());
        System.out.println(real.getPasswd());

        if(id.equals(real.getId()) && real.getPasswd().equals(passwd)) {
            String token = jwtUtil.createJWT(real.getId(), real.getId(), "user");

            return new LoginMsg(token,null);
        }
        else{

            return new LoginMsg(null,"账户密码不正确");
        }

    }


}
