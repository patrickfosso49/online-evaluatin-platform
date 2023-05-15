package com.projet.classwork.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Questionnaire;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
   
    public Questionnaire findByQuestionnaire(Long id);

    public List<Evaluation> findByClasse(Classe classe);
}
