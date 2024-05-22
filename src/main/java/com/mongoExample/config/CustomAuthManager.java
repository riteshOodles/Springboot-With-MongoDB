package com.mongoExample.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mongoExample.model.User;
import com.mongoExample.repo.UserRepo;

@Component
public class CustomAuthManager implements AuthenticationManager {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        User user = userRepo.findByEmail(username).get();
        if (user == null) {
            throw new BadCredentialsException("Username Password doesnot match !!");
        }
        String enPassword = new BCryptPasswordEncoder().encode(password);
        if (!enPassword.equals(user.getPass())) {
            throw new BadCredentialsException("Username Password doesnot match !!");
        }
        if (!user.isEnabled()) {
            throw new DisabledException("User is blocked");
        }
        List<String> userRoleList = user.getRoles();
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userRoleList.get(0)));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

}
