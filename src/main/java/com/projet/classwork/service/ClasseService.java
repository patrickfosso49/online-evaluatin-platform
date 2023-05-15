package com.projet.classwork.service;

import org.springframework.stereotype.Service;

import com.projet.classwork.model.Classe;
import com.projet.classwork.repository.ClasseRepository;

@Service
public class ClasseService {
   
    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public Classe findById(Long id) {
        try {
           return classeRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Classe save(Classe classe) {
        
        try {
            return classeRepository.save(classe);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Classe update(Classe classe) {
        try {
            return classeRepository.save(classe);
        }
        catch (Exception e) {
            return null;
        }
    }

    
}
