package com.material.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.material.types.Record;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class recordQuery implements GraphQLQueryResolver {

    @Resource
    private MongoTemplate mongoTemplate;

    public List<Record> allRecord() {
        return mongoTemplate.findAll(Record.class);
    }

    public Record recordByID(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Record.class);
    }

    public Long toReturn(){
        Query query = new Query(Criteria.where("isReturn").is(false));
        return mongoTemplate.count(query,Record.class);
    }
}
