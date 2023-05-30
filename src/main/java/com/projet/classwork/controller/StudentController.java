package com.projet.classwork.controller;

import java.util.List;

import com.projet.classwork.model.*;
import com.projet.classwork.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final QuestionnaireService questionnaireService;
    private final SubmissionService submissionService;
    private final PropositionService propositionService;
    private final EvaluationService evaluationService;
    private final ClasseService classeService;

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


}
