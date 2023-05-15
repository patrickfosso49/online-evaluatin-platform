package com.projet.classwork.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Question;
import com.projet.classwork.repository.EvaluationRepository;
import com.projet.classwork.repository.QuestionRepository;
import com.projet.classwork.service.ClasseService;
import com.projet.classwork.service.EvaluationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projet.classwork.model.Classe;

@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {
  
    @Autowired QuestionRepository questionRepository;
    private EvaluationService evaluationService;

    @Autowired ClasseService classeService;
   
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }


    @GetMapping("/")
    public List<Evaluation> findAll() {
        try {
            return evaluationService.findAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Evaluation findById(@PathVariable("id") Long id) {

        return evaluationService.findById(id);
    }

    @GetMapping("{id}/questions")
    public List<Question> findQuestionsByQuestionnaire(@PathVariable("id") Long id) {
        
        try {
            return questionRepository.findByQuestionnaire(id);
        }
        catch (Exception e) {
            return null;
        }
    }
    @GetMapping(value="/questionnaires/questions/{id}")
    public Question findQuestion(@PathVariable Long id) {
        try {
            return questionRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    @PostMapping(value="/")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation) {

        Classe classe = classeService.findById(2L);
        evaluation.setClasse(classe);
        return evaluationService.save(evaluation);
    }
    
}
