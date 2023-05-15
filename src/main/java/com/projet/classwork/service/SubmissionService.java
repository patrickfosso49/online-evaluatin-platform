package com.projet.classwork.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.model.Student;
import com.projet.classwork.model.Submission;
import com.projet.classwork.repository.SubmissionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;

@Service
public class SubmissionService {
    
    private final SubmissionRepository submissionRepository;
    private final EntityManagerFactory entityManagerFactory;

    public SubmissionService (SubmissionRepository submissionRepository, EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.submissionRepository = submissionRepository;
    }

    @Transactional
    public Submission create (Submission submission, Long studentid, Long questionnaireId) {

        List<Proposition> propositions = submission.getPropositions();
        for (Proposition proposition: propositions) {
            proposition.setSubmission(submission);
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        
        Student student = entityManager.find(Student.class, studentid);
        
        submission.setTime(LocalDateTime.now());
        submission.setStudent(student);
        submission.setPropositions(propositions);
        Submission body = entityManager.merge(submission);
        entityTransaction.commit();


        return body;
    }


    public List<Submission> findAll() {
        
        try {
            return submissionRepository.findAll();
        }
        catch(Exception e) {
            return null;
        }
    }

    public Submission save (Submission submission) {
        try {
            return submissionRepository.save(submission);
        }
        catch (Exception e) {
            return null;
        }
    }


    public List<Submission> findByStudent(Student student) {

        try {
            return submissionRepository.findByStudent(student);

       }
       catch (Exception e) {
            return null;
       }
    }
}
