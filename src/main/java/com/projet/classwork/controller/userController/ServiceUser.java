package com.projet.classwork.controller.userController;


import com.projet.classwork.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUser implements UserInterface {

    private static List<User>liste= new ArrayList<>();

    static {
     User e = new User();
        e.setId(45L);
        e.setFirstName("prunelle");
        e.setLastName("eba");
        e.setEmail("prunelle@gmail.com");
        e.setPassword("prunelle123");
        liste.add(e);
        e = new User();
        e.setId(46L);
        e.setFirstName("chantal");
        e.setLastName("eba");
        e.setEmail("chantal@gmail.com");
        e.setPassword("chantal123");
        liste.add(e);
    }



    public List<User> getUser() {
        return liste;
    }
}
