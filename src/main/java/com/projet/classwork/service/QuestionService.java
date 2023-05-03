package com.projet.classwork.service;

import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionService {
    public Question saveQuestion(Question question);

    public Optional<Question> findQuestionById(Long id);

    List<Question> fetchAllEQuestions();

    void deleteQuestionyId(Long Id);
}
