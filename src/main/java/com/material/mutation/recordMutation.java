package com.material.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.material.types.Good;
import com.material.types.Record;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class recordMutation implements GraphQLMutationResolver {
    @Resource
    private MongoTemplate mongoTemplate;

    public String createRecord(Record record, DataFetchingEnvironment environment) {

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        record.setDate(now.toString());
        record.setId(simpleDateFormat.format(now));
        record.setReturn(false);

        //循环插入吧，后面再优化
        try {
            for (Good item : record.getItems()) {
                Query query = new Query(Criteria.where("id").is(item.getId()));
                Update update = new Update();
                Good real_item = mongoTemplate.findOne(query, Good.class);
                real_item.setBorrow(real_item.getBorrow() + item.getBorrow());
                update.set("borrow", real_item.getBorrow());
                mongoTemplate.updateFirst(query, update, Good.class);
                update.set("brokenDes", item.getBrokenDes());
                mongoTemplate.updateFirst(query, update, Good.class);

                //补全item
                item.setName(real_item.getName());
            }
        } catch (Exception e) {
            throw new GraphQLException("空指针了");
        }
        mongoTemplate.save(record);

        return record.getId();
    }

    public Boolean returnRecord(Record record) {
        Query query = new Query(Criteria.where("id").is(record.getId()));
        Record real = mongoTemplate.findOne(query, Record.class);
        if (real == null || real.getReturn()) {
            return false;
        }
        Update update = new Update();
        update.set("isReturn", true);
        mongoTemplate.updateFirst(query, update, Record.class);
        update.set("note", record.getNote());
        mongoTemplate.updateFirst(query, update, Record.class);

        return true;
    }
}
