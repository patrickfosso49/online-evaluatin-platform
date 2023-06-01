package com.projet.classwork.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

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
    private final SubmissionService submissionService;

    private final ClasseService classeService;

    private final CopyService copyService;


    
    public StudentController(StudentService studentService,
                             SubmissionService submissionService, ClasseService classeService, ClasseService classeService1, CopyService copyService) {

           this.studentService = studentService;

           this.submissionService = submissionService;
        this.classeService = classeService1;

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


    @PutMapping("/{id}/classes/{classeId}/")
    public ResponseEntity joinClasse(@PathVariable("id") Long studentId, @PathVariable("classeId") Long classeId) {
        System.out.println("id is => "+ studentId);
        Student student = studentService.findById(studentId);
        System.out.println("student => "+student.getEmail());

        if(student == null){
            System.out.println("ok null");
            return ResponseEntity.badRequest().build();
        }

        ConcurrentHashMap<Long, Classe> classMap = new ConcurrentHashMap<>();
        List<Classe> classes = student.getClasses();
        List<Classe> classCopy = new ArrayList<>(classes);
        for (Classe c : classCopy) {
            classMap.put(c.getId(), c);
        }
        if (classes.isEmpty()){
            System.out.println("NO classes yet");
            Classe classe = classeService.findById(classeId);
            classe.addStudent(student);
            student = studentService.save(student);
            return ResponseEntity.ok(student);
        }

        Student body = null;
      //  ConcurrentHashMap<Long, Classe> newClasses = new ConcurrentHashMap<>(classMap);


        Classe classe = classeService.findById(classeId);
        classe.addStudent(student);
        body = studentService.save(student);
        if (body == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(student);
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
