package com.mongoExample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mongoExample.services.impl.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point; // used to show message when user is not authenticated
    @Autowired
    private JwtAuthenticationFilter filter;
    // @Autowired
    // private UserDetailsService userDetailsService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        // provider.setUserDetailsService(new UserDetailsService());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/**").permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> {
                    logout.logoutUrl("/auth/logout");
                    logout.invalidateHttpSession(true);
                    logout.deleteCookies("JSESSIONID");
                });
        return http.build();
    }

}