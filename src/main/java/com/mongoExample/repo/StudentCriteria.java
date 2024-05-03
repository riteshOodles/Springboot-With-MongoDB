package com.mongoExample.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongoExample.model.Student;

@Repository
public class StudentCriteria {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Student> findByProject(String projectName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("projects.name").is(projectName));
        return mongoTemplate.find(query, Student.class);
    }
}
