package com.material.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.material.types.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component

public class userQuery implements GraphQLQueryResolver {

    @Resource
    private MongoTemplate mongoTemplate;

    public List<User> allUser() {
        return mongoTemplate.findAll(User.class);
    }

    public Boolean login(String id, String passwd) {
        Query query = new Query(Criteria.where("id").is(id));
        try {
            User real = mongoTemplate.findOne(query, User.class);
            return passwd.equals(real.getPasswd());
        } catch (NullPointerException e) {
            return false;
        }
    }
}
