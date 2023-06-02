package com.projet.classwork.controller;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.model.Student;
import com.projet.classwork.model.Submission;
import com.projet.classwork.repository.PropositionRepository;
import com.projet.classwork.repository.QuestionRepository;
import com.projet.classwork.repository.QuestionnaireRepository;
import com.projet.classwork.repository.StudentRepository;
import com.projet.classwork.repository.SubmissionRepository;

@SpringBootTest
public class SubmissionControllerTest {
   
    @Autowired private SubmissionRepository submissionRepository;

    @Autowired StudentRepository studentRepository;

    @Autowired QuestionnaireRepository questionnaireRepository;

    @Autowired QuestionRepository questionRepository;

    @Autowired PropositionRepository propostionRepository;
    @Test
    public void saveTest() {

        Student student = studentRepository.findById(1L).get();

        Questionnaire questionnaire = questionnaireRepository.findById(1L).get();
        
        Submission submission = new Submission();

        submission.setId(null);
        submission.setMark(null);
        submission.setStudent(student);
        submission.setTime(LocalDateTime.now());
        submission.setQuestionnaire(questionnaire);
        

        Assertions.assertNotNull(submissionRepository.save(submission));
    }

    @Test
    public void saveProposition() {
        Proposition proposition = new Proposition();

        Submission submission = submissionRepository.findById(1L).get();
        
        proposition.setSubmission(submission);
        proposition.setQuestion(questionRepository.findById(1L).get());

        List<String> values = new ArrayList<>();
        values.add("Answer");
        proposition.setValues(values);

        Assertions.assertNotNull(propostionRepository.save(proposition));
    
    }
}
