package com.projet.classwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.classwork.model.Classe;
import com.projet.classwork.service.ClasseService;

@RestController
@RequestMapping("/api/v1/classes")
public class ClasseController {
   
    private final ClasseService classeService;

    public ClasseController (ClasseService classeService) {
        this.classeService = classeService;
    }

    @PostMapping("/")
    public Classe create(@RequestBody Classe classe) {
        return classeService.save(classe);
    }

    @PutMapping("/{id}")
    public Classe update(@RequestBody Classe classe) {

        return classeService.update(classe);
    }    
}
