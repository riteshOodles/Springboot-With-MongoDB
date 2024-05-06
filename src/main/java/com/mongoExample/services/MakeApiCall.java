package com.mongoExample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mongoExample.feignClient.UseFeignClient;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MakeApiCall {

    @Autowired
    private UseFeignClient useFeignClient;

    // URL - https://reqres.in/api/users?page=2

    public ResponseEntity<String> callingApiUsingRest() {
        String url = "https://reqres.in/api/users?page=2";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            log.warn("No data found");
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<String> callingApiUsingFeign() {
        ResponseEntity<String> response = useFeignClient.getUsers(2);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            log.warn("No data found");
            return ResponseEntity.internalServerError().build();
        }
    }
}
