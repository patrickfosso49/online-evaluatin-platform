package com.projet.classwork.service;

import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.repository.evaluation.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireServiceImpl implements  QuestionnaireService{

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Override
    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public Optional<Questionnaire> findQuestionnaireById(Long id) {
        return questionnaireRepository.findById(id);
    }

    @Override
    public List<Questionnaire> fetchAllEQuestionnaires() {
        return questionnaireRepository.findAll();
    }

    @Override
    public void deleteQuestionnaireById(Long Id) {
        questionnaireRepository.deleteById(Id);
    }
}
