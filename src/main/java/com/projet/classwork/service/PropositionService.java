package com.projet.classwork.service;

import com.projet.classwork.model.Proposition;

import java.util.List;
import java.util.Optional;

public interface PropositionService {
    public Proposition saveProposition(Proposition proposition);

    public Optional<Proposition> findPropositionById(Long id);

    List<Proposition> fetchAllProposition();

    void deletePropositionById(Long Id);

    Long getQuestionID(Long propositionID);

    Long getSubmissionID(Long propositionID);


}
