package com.projet.classwork.service;

import com.projet.classwork.model.Question;
import com.projet.classwork.model.Questionnaire;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface QuestionnaireService {
    public Questionnaire saveQuestionnaire(Questionnaire questionnaire);

    public Optional<Questionnaire> findQuestionnaireById(Long id);

    List<Questionnaire> fetchAllEQuestionnaires();

    void deleteQuestionnaireById(Long Id);
}
