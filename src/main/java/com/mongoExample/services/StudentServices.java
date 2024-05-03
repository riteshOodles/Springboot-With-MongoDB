package com.mongoExample.services;

import java.util.List;
import java.util.Optional;

import com.mongoExample.model.Student;

public interface StudentServices {
    Optional<Student> addStudent(Student student);

    Optional<Student> findStudentById(String id);

    Optional<Student> findStudentByRollNo(int rollNo);

    List<Student> findByProject(String projectName);

    List<Student> findAllStudent();

    Optional<Student> updateStudent(Student student);

    boolean deleteById(String id);

    boolean deleteAll();

}
