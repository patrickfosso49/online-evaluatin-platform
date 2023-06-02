package com.projet.classwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.classwork.model.Student;
import com.projet.classwork.pojo.TokenRequest;
import com.projet.classwork.service.StudentService;
import com.projet.classwork.service.StudentUserDetailsService;
import com.projet.classwork.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/students")
@RequiredArgsConstructor
public class StudentAuthController {
   
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<?> token (Authentication authentication) {


        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok().body(token);
    }


 

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@RequestBody Student student) {
        if(studentService.existsByEmail(student.getEmail()))
        return ResponseEntity.badRequest().body("email already existing");

        if(studentService.existsByRegistrationNumber(student.getRegistrationNumber()))
        return ResponseEntity.badRequest().body("registration number already existing");

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student = studentService.save(student);

        if(student == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(student);

    }

}
