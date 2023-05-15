package com.projet.classwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.repository.PropositionRepository;

@Service
public class PropositionService {
    
    private final PropositionRepository propositionRepository;

    public PropositionService (PropositionRepository propositionRepository) {
        this.propositionRepository = propositionRepository;
    }

    public List<Proposition> saveAll( List<Proposition> propositions) {

        try {
            return propositionRepository.saveAll(propositions);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
