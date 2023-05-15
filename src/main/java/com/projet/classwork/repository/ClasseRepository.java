package com.projet.classwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.classwork.model.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    
}
