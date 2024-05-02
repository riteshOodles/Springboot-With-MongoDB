package com.mongoExample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
// @Document
public class Student {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;
    private String college;

    /*
    JSON
    {
    "id":1,
    "name":"Ritesh Kumar",
    "college":"abcd"
    }
     */

    public Student() {
    }

    public Student(String name, String college) {
        this.name = name;
        this.college = college;
    }

    public Student(int id, String name, String college) {
        this.id = id;
        this.name = name;
        this.college = college;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", college=" + college + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
