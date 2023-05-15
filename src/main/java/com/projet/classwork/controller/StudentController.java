package com.projet.classwork.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.classwork.service.ClasseService;
import com.projet.classwork.service.EvaluationService;
import com.projet.classwork.service.PropositionService;
import com.projet.classwork.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projet.classwork.dto.SubmissionDto;
import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Proposition;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.model.Student;
import com.projet.classwork.model.Submission;
import com.projet.classwork.service.SubmissionService;
import com.projet.classwork.service.QuestionnaireService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;
    private final QuestionnaireService questionnaireService;
    private final SubmissionService submissionService;
    private final PropositionService propositionService;
    private final EvaluationService evaluationService;
    private final ClasseService classeService;


    
    public StudentController(StudentService studentService,QuestionnaireService questionnaireService, PropositionService propositionService,
        SubmissionService submissionService, EvaluationService evaluationService, ClasseService classeService) {
                
           this.studentService = studentService;
           this.questionnaireService = questionnaireService;
           this.propositionService = propositionService;
           this.submissionService = submissionService;
           this.evaluationService = evaluationService;
           this.classeService = classeService;
    }


    @PostMapping("")
    public ResponseEntity<?> create (@RequestBody Student student) {
        Student body = studentService.save(student);

        if (body == null) return ResponseEntity.badRequest().build();
        
        return ResponseEntity.created(null).body(body);
    }

    @PostMapping("/{id}/classes/{classeId}/questionnaires/{questionnaireId}/submissions")
    public ResponseEntity<?> createSubmission(@PathVariable("id") Long id, @PathVariable("questionnaireId") Long questionnaireId, @RequestBody Submission submission) {
        Student student = studentService.findById(id);
        if(student == null) return ResponseEntity.noContent().build();

        for(Classe classe :student.getClasses()) {
            
            for(Evaluation evaluation: classe.getEvaluations()) {

                if (evaluation.getQuestionnaire().getId() == questionnaireId) {
                    Submission body = submissionService.create(submission, id, questionnaireId);

                    return (body == null) ? ResponseEntity.internalServerError().build() : ResponseEntity.created(null).body(body);
                }
            }
        } 
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<Student> body = studentService.findAll();

        return (body == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> read (@PathVariable("id") Long id) {

        Student student = studentService.findById(id);
        if (student == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(student);

    }


    @PutMapping("/{id}/classes/{classeId}")
    public ResponseEntity<?> joinClasse(@PathVariable("id") Long id, @PathVariable("classeId") Long classeId) {

        Student body = studentService.findById(id);
        Classe classe = classeService.findById(classeId);
         
        body = studentService.save(body);

        if (body == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(body);

    }



}
