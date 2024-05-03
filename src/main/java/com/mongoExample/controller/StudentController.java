package com.mongoExample.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.mongoExample.model.Student;
import com.mongoExample.services.impl.StudentServicesImpl;

@RestController
@RequestMapping("/students")
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

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Optional<Student> studentOptional = studentServices.findStudentById(id);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/roll/{rollNo}")
    public ResponseEntity<Student> getStudentByroll(@PathVariable int rollNo) {
        Optional<Student> studentOptional = studentServices.findStudentByRollNo(rollNo);
        if (studentOptional.isPresent()) {
            return ResponseEntity.ok(studentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/project/{projectName}")
    public ResponseEntity<List<Student>> getStudentByProject(@PathVariable String projectName) {
        return ResponseEntity.ok(studentServices.findByProject(projectName));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentServices.findAllStudent());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
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
