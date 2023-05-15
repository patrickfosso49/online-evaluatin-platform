package com.projet.classwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.repository.EvaluationRepository;

@Service
public class EvaluationService {
    
    private EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public Evaluation findById(Long id) {

        try {
            return evaluationRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Evaluation save(Evaluation evaluation) {
        
        try {
            return evaluationRepository.save(evaluation);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public List<Evaluation> findAll() {
        
        try {
            return evaluationRepository.findAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Evaluation> findEvaluations(Classe classeId) {

        try {
            return evaluationRepository.findByClasse(classeId);
        }
        catch (Exception e) {
            
            e.printStackTrace();
            return null;
        }
    }

}
