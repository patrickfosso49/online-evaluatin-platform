package com.projet.classwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.classwork.model.Proposition;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {
    
}
