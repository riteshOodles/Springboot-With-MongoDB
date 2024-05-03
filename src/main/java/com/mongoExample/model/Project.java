package com.mongoExample.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Document
public class Project {
    // @Id
    // private String id;
    private String name;
    private int durationInMonths;
    private String description;
    private List<String> skills;
}
