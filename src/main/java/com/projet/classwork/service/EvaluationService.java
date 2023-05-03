package com.projet.classwork.service;

import com.projet.classwork.model.Evaluation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface EvaluationService {
    public Evaluation saveEvaluation(Evaluation evaluation);

    public Optional<Evaluation> findEvaluationById(Long id);

    List<Evaluation> fetchAllEvaluation();

    Evaluation updateEvaluation(Evaluation evaluation, Long evaluationId);

    void deleteEvaluationById(Long Id);
}
