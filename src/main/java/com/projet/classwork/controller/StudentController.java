package com.projet.classwork.controller;

import java.util.List;

import com.projet.classwork.model.*;
import com.projet.classwork.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projet.classwork.service.ClasseService;
import com.projet.classwork.service.MailService;
import com.projet.classwork.service.StudentService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Student;
import com.projet.classwork.model.Submission;
import com.projet.classwork.service.SubmissionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final SubmissionService submissionService;
    private final ClasseService classeService;
    private final MailService mailService; 


    private final CopyService copyService;


    
    public StudentController(StudentService studentService, QuestionnaireService questionnaireService, PropositionService propositionService,
                             SubmissionService submissionService, EvaluationService evaluationService, ClasseService classeService, CopyService copyService) {
                
           this.studentService = studentService;
           this.questionnaireService = questionnaireService;
           this.propositionService = propositionService;
           this.submissionService = submissionService;
           this.evaluationService = evaluationService;
           this.classeService = classeService;
           this.copyService = copyService;
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

                    if (body == null) {
                        return ResponseEntity.badRequest().build();
                    }
                    mailService.sendEmail(student.getEmail(), evaluation.getTitle(), "Hi, your mark is available for this evaluation on classwork"); 
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

        Student student = studentService.findById(id);
        
        if(student == null) return ResponseEntity.badRequest().build();
        Student body = null;
        for (Classe classe: student.getClasses()) {
            if (classe.getId() == classeId) {
                classe.addStudent(student);
                body = studentService.save(student);
                
            }
        }

        if (body == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(body);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody Student student) {
        Student body = studentService.findById(id);
        body.setEmail(student.getEmail());
        body = studentService.save(body);
        if(body == null){ return ResponseEntity.badRequest().build();}

    @PostMapping("/{id}/copy/assignment/{assignmentId}")
    public ResponseEntity<?> SubmitCopy(
            @PathVariable("id") Long studentId, @PathVariable("assignmentId") Long assignmentId,
            @RequestBody Copy copy) {
        Student student = studentService.findById(studentId);
        if(student == null) return ResponseEntity.noContent().build();

        System.out.println("Here is the copy; "+copy.toString());
        for(Classe classe :student.getClasses()) {
            for(Assignment assignment: classe.getAssignments()) {

                if (assignment.getId() == assignmentId) {
                    System.out.println("HIIIIIIIIII");
                    Copy body = copyService.createStudentCopy(copy, studentId, assignmentId);

                    return (body == null) ? ResponseEntity.internalServerError().build() : ResponseEntity.created(null).body(body);
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/copy/{id}")
    public List<Copy> findCopiesByStudent(@PathVariable("id") Long studentId) {
        try {
            return copyService.findByStudent(studentId);
        }
        catch (Exception e) {
            return null;
        }
    }


        return ResponseEntity.ok(body);
    }

}
