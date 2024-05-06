package com.mongoExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongoExample.services.MakeApiCall;

@RestController
@RequestMapping("/call-dummy")
public class DemoApiCalling {

    @Autowired
    MakeApiCall makeApiCall;

    @GetMapping("/users/rest")
    public ResponseEntity<String> getAllUsersUsingRest() {
        return makeApiCall.callingApiUsingRest();
    }

    @GetMapping("/users/feign")
    public ResponseEntity<String> getAllUsersUsingFeign() {
        return makeApiCall.callingApiUsingFeign();
    }
}
