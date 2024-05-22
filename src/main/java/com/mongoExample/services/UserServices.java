package com.mongoExample.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoExample.model.User;
import com.mongoExample.repo.UserRepo;

@Service
public class UserServices {
    @Autowired
    private UserRepo userRepo;

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

}
