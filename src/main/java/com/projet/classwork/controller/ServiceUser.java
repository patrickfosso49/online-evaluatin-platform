package com.projet.classwork.controller;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUser implements UserInterface{

    private static List<User>liste= new ArrayList<>();

    static {
        User e = new User();
        e.setId(45);
        e.setFirstname("prunelle");
        e.setLastname("jeanne");
        e.setPassword("hrfpor53");
        liste.add(e);
    }


    @Override
    public List<User> getUser() {
        return liste;
    }
}
