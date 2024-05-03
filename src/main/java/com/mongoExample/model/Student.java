package com.mongoExample.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Document(collection = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Student {

    @Id
    private String id;

    // @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    @Indexed(unique = true)
    private Integer rollNo;

    @Indexed(unique = true)
    private String name;
    private String college;

    private Address address;

    // @DBRef
    private List<Project> projects;

    /*
    JSON
    {
    "rollNo":1,
    "name":"Ritesh Kumar",
    "college":"abcd",
    "address":{
        "city":"Pune",
        "state":"Maharashtra",
        "country":"India"
    }
    projects:[
        {
            "name":"Java",
            "timeInMonths":12,
            "description":"Java is a high-level, class-based, object-oriented programming language",
            "skills":["Java","Spring","Hibernate"]
        },
        {
            "name":"Spring",
            "timeInMonths":6,
            "description":"Spring is an open source Java framework",
            "skills":["Spring","Hibernate","SpringBoot"]
        }
    }
     */

    // public Student() {
    // }

    public Student(String name, String college, Address address) {
        this.name = name;
        this.college = college;
        this.address = address;
    }

    // public Student(int id, String name, String college, Address address) {
    //     this.id = id;
    //     this.name = name;
    //     this.college = college;
    //     this.address = address;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getCollege() {
    //     return college;
    // }

    // public void setCollege(String college) {
    //     this.college = college;
    // }

    // @Override
    // public String toString() {
    //     return "Student [id=" + id + ", name=" + name + ", college=" + college + "]";
    // }

    // public String getId() {
    //     return id;
    // }

    // public void setId(String id) {
    //     this.id = id;
    // }

}
