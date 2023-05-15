package com.projet.classwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Question;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.repository.QuestionnaireRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;


@Service
public class QuestionnaireService {
    
    private final QuestionnaireRepository questionnaireRepository;
    private final EntityManagerFactory entityManagerFactory;
    
    public QuestionnaireService(QuestionnaireRepository questionnaireRepository, EntityManagerFactory entityManagerFactory) {
        this.questionnaireRepository = questionnaireRepository;
        this.entityManagerFactory = entityManagerFactory;
    }


    public Questionnaire findById(Long id) {
        try {
            return questionnaireRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    @Transactional
    public Questionnaire create (Questionnaire questionnaire, Long evaluationId) {
       
        List<Question> questions = questionnaire.getQuestions();        
        for (Question question: questions) {
            question.setQuestionnaire(questionnaire);
        }


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
       
        Evaluation evaluation = entityManager.find(Evaluation.class, evaluationId);

        questionnaire.setEvaluation(evaluation);
        entityManager.merge(questionnaire);

        entityTransaction.commit();
        entityManager.close();
        return null;
    }
}
