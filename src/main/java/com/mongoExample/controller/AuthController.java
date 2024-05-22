package com.mongoExample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongoExample.dto.JwtResponse;
import com.mongoExample.model.User;
import com.mongoExample.services.UserServices;
import com.mongoExample.utils.JwtHelper;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        if (userServices.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("User Already Exists !!", HttpStatus.BAD_REQUEST);
        }

        user.setEmail(user.getEmail().toLowerCase());
        user.setRoles(List.of("USER"));
        user.setPass(new BCryptPasswordEncoder().encode(user.getPass()));
        User saveUser = userServices.saveUser(user);

        if (saveUser != null) {
            return new ResponseEntity<>("User Registered Successfully !!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Cannot register User!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody User user) {

        this.doAuthenticate(user.getEmail(), user.getPassword());

        UserDetails userDetails = userServices.findByEmail(user.getEmail()).get();
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
