package com.projet.classwork.service;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.repository.evaluation.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropositionServiceImpl implements PropositionService{

    @Autowired
    PropositionRepository propositionRepository;

    @Override
    public Proposition saveProposition(Proposition proposition) {
        return propositionRepository.save(proposition);
    }

    @Override
    public Optional<Proposition> findPropositionById(Long id) {
        return propositionRepository.findById(id);
    }

    @Override
    public List<Proposition> fetchAllProposition() {
        return propositionRepository.findAll();
    }

    @Override
    public void deletePropositionById(Long Id) {
        propositionRepository.deleteById(Id);
    }

    @Override
    public Long getQuestionID(Long propositionID) {
        return propositionRepository.getQuestionID(propositionID);
    }


    @Override
    public Long getSubmissionID(Long propositionID) {
        return propositionRepository.getSubmissionID(propositionID);
    }

}
