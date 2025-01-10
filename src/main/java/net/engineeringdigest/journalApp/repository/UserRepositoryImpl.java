package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(
                criteria.orOperator(Criteria.where("email").exists(true),
                Criteria.where("sentimentAnalysis").is(true))
        );
        return mongoTemplate.find(query, User.class);
    }
}
