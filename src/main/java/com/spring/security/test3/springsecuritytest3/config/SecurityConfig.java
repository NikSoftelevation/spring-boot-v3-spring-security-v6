package com.spring.security.test3.springsecuritytest3.config;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails normalUSer= User
                                        .withUsername("Nikhil")
                                        .password(passwordEncoder().encode("Nikhil"))
                                        .roles("NORMAL")
                                        .build();

    UserDetails adminUser=User
                                  .withUsername("Nikhil1")
                                  .password(passwordEncoder().encode("Nikhil1"))
                                  .roles("ADMIN")
                                  .build();

      InMemoryUserDetailsManager inMemoryUserDetailsManager  =new InMemoryUserDetailsManager(normalUSer,adminUser);
      return inMemoryUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        try {
            return httpSecurity
                           .csrf()
                           .disable()
                           .authorizeHttpRequests()
                       // .requestMatchers("/home/admin")
                       //  .hasRole("ADMIN")
                       //    .requestMatchers("/home/normal")
                       //    .hasRole("NORMAl")
                           .requestMatchers("/home/public")
                           .permitAll()
                           .anyRequest()
                           .authenticated()
                           .and()
                           .formLogin()
                           .and()
                           .build();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}