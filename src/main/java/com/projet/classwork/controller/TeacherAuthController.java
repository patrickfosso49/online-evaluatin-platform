package com.projet.classwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.classwork.model.Teacher;
import com.projet.classwork.service.TeacherService;
import com.projet.classwork.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/teachers")
@RequiredArgsConstructor
public class TeacherAuthController {
  
    private final TokenService tokenService;
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/token")
    public String token (Authentication authentication) {
        String token = tokenService.generateToken(authentication);

        return token;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@RequestBody Teacher teacher) {
        
        if(teacherService.existsByEmail(teacher.getEmail()))
        return ResponseEntity.badRequest().body("Email already existing");
        

        if(teacherService.existsByRegistrationNumber(teacher.getRegistrationNumber()))
        return ResponseEntity.badRequest().body("Registration number already existing");
        
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher = teacherService.save(teacher);
       
        if (teacher == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(teacher);
    }


}
