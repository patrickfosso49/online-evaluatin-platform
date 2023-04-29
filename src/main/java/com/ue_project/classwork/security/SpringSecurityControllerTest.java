package com.ue_project.classwork.security;


import com.ue_project.classwork.security.config.AuthHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthHelper.REQUEST_MAPPING_PATH)
public class SpringSecurityControllerTest {


    @GetMapping("/secu")
    public ResponseEntity<String> getS(){
        return ResponseEntity.ok("Fucking done...");
    }
}
