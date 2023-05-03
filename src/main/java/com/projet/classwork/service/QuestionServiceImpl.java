package com.projet.classwork.service;

import com.projet.classwork.model.Question;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.repository.evaluation.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> fetchAllEQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void deleteQuestionyId(Long Id) {questionRepository.deleteById(Id);}
}
