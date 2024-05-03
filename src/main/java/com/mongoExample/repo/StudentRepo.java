package com.mongoExample.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongoExample.model.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student, String> {
    Optional<Student> findByRollNo(int rollNo);
}
