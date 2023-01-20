package com.spring.security.test3.springsecuritytest3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class TestController {

    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/normal")
    public ResponseEntity<String> normalUser() {

        return ResponseEntity.ok("Yes,i'm a normal user");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminUser(){

        return ResponseEntity.ok("Yes i'm the admin user");
    }
    @GetMapping("/public")
    public ResponseEntity<String> publicUser(){

        return ResponseEntity.ok("this is for public access");
    }
}
