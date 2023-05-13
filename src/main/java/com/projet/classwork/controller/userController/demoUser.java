package com.projet.classwork.controller.userController;
import com.projet.classwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("api/user")
public class demoUser {

    @Autowired
    private UserInterface eService;

    @GetMapping("user")
    public List<User> getUser(){
        return eService.getUser();
    }
}



