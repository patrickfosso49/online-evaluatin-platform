package com.projet.classwork.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.repository.ClasseRepository;
import com.projet.classwork.repository.EvaluationRepository;

@SpringBootTest
public class EvaluationControllerTest {
  
    @Autowired private ClasseRepository classRepository;

    @Autowired private EvaluationRepository evaluationRepository;
    @Test
    public void saveEvaluationTest () {

        Classe class1 = classRepository.findById(1L).get();

        Questionnaire questionnaire = new Questionnaire();

        LocalDateTime now = LocalDateTime.now();

        Evaluation evaluation = new Evaluation(null, "Quiz on introduction to operating systems", "Answer all questions", LocalDateTime.now(), now.plusHours(2),null,class1);
        
        evaluation.setQuestionnaire(questionnaire);

        Assertions.assertNotNull(evaluationRepository.save(evaluation));
    }
}
