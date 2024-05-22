package com.mongoExample.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mongoExample.model.User;
import com.mongoExample.repo.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepo.findByEmail(username);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found for " + username + "!!");
        }
        return userOptional.get();
    }

}
