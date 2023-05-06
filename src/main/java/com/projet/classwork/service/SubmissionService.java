package com.projet.classwork.service;

import com.projet.classwork.model.Submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionService {
    public Submission save(Submission submission);

    public Optional<Submission> loadById(Long ID);

    public List<Submission> fetchAllSubmission();

    public Optional<Submission> findSubmissionByID(Long id);

    public void deleteSubmissionById(Long ID);

    public Submission updateSubmission(Submission submission, Long ID);

    public Submission correctSubmission(Long submissionID);
}
