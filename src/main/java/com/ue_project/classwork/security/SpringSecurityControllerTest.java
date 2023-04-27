package com.ue_project.classwork.security;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class SpringSecurityControllerTest {


    @GetMapping("/secu")
    public ResponseEntity<String> getS(){
        return ResponseEntity.ok("Fucking done...");
    }
}
