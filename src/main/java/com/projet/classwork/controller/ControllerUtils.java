package com.projet.classwork.controller;

import java.util.List;

import org.springframework.stereotype.Component;


import com.projet.classwork.model.Classe;

@Component
public class ControllerUtils {

    

    public static boolean validateIntegrity(List<Classe> classes, Long id) {
        int position = 0;
        for (Classe classe : classes) {
            
            if (classe.getId() == id) break;
            ++position;
        }

        return (position == classes.size() ? true : false);
    }
}
