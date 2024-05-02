package com.mongoExample.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mongoExample.model.Student;
import com.mongoExample.repo.StudentRepo;
import com.mongoExample.services.StudentServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentServicesImpl implements StudentServices {
    final private StudentRepo studentRepo;

    /**
     * @param id of the student
     * @return an Optional<Student> if present else an empty Optional
     */
    public Optional<Student> findStudentById(int id) {
        try {
            return studentRepo.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Student> addStudent(Student student) {
        try {
            Student savedStudent = studentRepo.save(student);
            return Optional.of(savedStudent);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    public List<Student> findAllStudent() {
        try {
            return studentRepo.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return List.of();
        }
    }

    public Optional<Student> updateStudent(Student student) {
        try {
            if (studentRepo.existsById(student.getId())) {
                Student updatedStudent = studentRepo.save(student);
                return Optional.of(updatedStudent);
            } else {
                throw new IllegalAccessException("Student does'nt exist with same id! Please try to create a new one.");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    public boolean deleteById(int id) {
        try {
            studentRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteAll() {
        try {
            studentRepo.deleteAll();
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

}
