package com.material.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.material.service.SecurityService;
import com.material.types.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class recordQuery implements GraphQLQueryResolver {

    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private SecurityService securityService;

    public List<Record> allRecord() {
        if(securityService.ifLogin()==true){
            return mongoTemplate.findAll(Record.class);
        }else{
            Record record = new Record("未登录");
            List<Record> list = new ArrayList<Record>();
            list.add(record);
            return list;
        }

    }

    public Record recordByID(String id) {
        if(securityService.ifLogin()==true) {
            Query query = new Query(Criteria.where("id").is(id));
            return mongoTemplate.findOne(query, Record.class);
        }
        else{
            return new Record("未登录");
        }
    }

    public Long toReturn(){
        if(securityService.ifLogin()==true) {
            Query query = new Query(Criteria.where("isReturn").is(false));
            return mongoTemplate.count(query, Record.class);
        }
        else{
            long a = -1;
            return a;
        }
    }
}
