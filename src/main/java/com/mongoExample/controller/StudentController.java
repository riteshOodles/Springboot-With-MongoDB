package com.mongoExample.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mongoExample.model.Student;
import com.mongoExample.services.impl.StudentServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServicesImpl studentServices;

    @PostMapping
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
        Optional<Student> studentOptional = studentServices.addStudent(student);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.badRequest().body(student);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> studentOptional = studentServices.findStudentById(id);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentServices.findAllStudent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        boolean isDeleted = studentServices.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Student removed having id = " + id);
        } else {
            return ResponseEntity.ok("Student does'nt exist or Internal issue!");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllStudent(@RequestParam(required = true) boolean isConform) {
        if (isConform) {
            boolean isDeleted = studentServices.deleteAll();
            if (isDeleted) {
                return ResponseEntity.ok("All students has been removed!");
            }
        }
        return ResponseEntity.ok("Unable to remove All Student!");
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Optional<Student> studentOptional = studentServices.updateStudent(student);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.badRequest().body(student);
        }

    }

}
