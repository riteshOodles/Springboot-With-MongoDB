package com.mongoExample.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "reqres", url = "https://reqres.in", path = "/api")
public interface UseFeignClient {

    @GetMapping("/users")
    public ResponseEntity<String> getUsers(@RequestParam int page);
}
