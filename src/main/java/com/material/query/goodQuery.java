package com.material.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.material.service.SecurityService;
import com.material.types.Good;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SecurityService securityService;

    public List<Good> allGoods() throws Exception{
        if(securityService.ifLogin() == true){
            return mongoTemplate.findAll(Good.class);
        }
        else{
            Good good = new Good("未登录");
            List<Good> list = new ArrayList<Good>();
            list.add(good);
            return list;
        }

    }

    public Good goodByID(Integer id){
        if(securityService.ifLogin() == true){
            Query query = new Query(Criteria.where("id").is(id));
            return mongoTemplate.findOne(query,Good.class);
        }
        else{
            return new Good("未登录");
        }

    }
}
