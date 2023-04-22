package com.projet.classwork.controller;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class demo {

    @Autowired
    private UserInterface eservice;

    @GetMapping("user")
    public List<User> getUser(){
        return eservice.getUser();
    }


}
