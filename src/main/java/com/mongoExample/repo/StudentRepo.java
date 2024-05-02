package com.mongoExample.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongoExample.model.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student, Integer> {

}
