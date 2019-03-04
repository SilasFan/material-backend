package com.material.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.material.types.Good;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class goodQuery implements GraphQLQueryResolver {
    @Resource
    private MongoTemplate mongoTemplate;

    public List<Good> allGoods(){
        return mongoTemplate.findAll(Good.class);
    }

    public Good goodByID(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query,Good.class);
    }
}
