package com.mongoExample.services;

import java.util.List;
import java.util.Optional;

import com.mongoExample.model.Student;

public interface StudentServices {
    Optional<Student> addStudent(Student student);

    Optional<Student> findStudentById(int id);

    List<Student> findAllStudent();

    Optional<Student> updateStudent(Student student);

    boolean deleteById(int id);

    boolean deleteAll();
}
