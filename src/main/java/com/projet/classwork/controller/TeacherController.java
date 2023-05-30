package com.projet.classwork.controller;

import java.util.ArrayList;
import java.util.List;

import com.projet.classwork.model.*;
import com.projet.classwork.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
   
    
    private final ClasseService classeService;
    private final TeacherService teacherService;
    private final EvaluationService evaluationService;
    private final QuestionnaireService questionnaireService;

    private final CopyService copyService;

    private final AssignmentService assignmentService;

    private final StudentService studentService;

    public TeacherController(TeacherService teacherService, ClasseService classeService,
                             EvaluationService evaluationService, QuestionnaireService questionnaireService, CopyService copyService, AssignmentService assignmentService, StudentService studentService) {
            this.teacherService = teacherService;
            this.classeService = classeService;
            this.evaluationService = evaluationService;
            this.questionnaireService = questionnaireService;
            this.copyService = copyService;
            this.assignmentService = assignmentService;
        this.studentService = studentService;
    }


    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Teacher teacher) {
        Teacher body = teacherService.save(teacher);
        
        if (body == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.created(null).body(body);

    }

    @PostMapping("/{id}/classes")
    public ResponseEntity<?> create( @PathVariable("id") Long id, @RequestBody Classe classe) {
        Teacher teacher = teacherService.findById(id);

        if(teacher == null) return ResponseEntity.noContent().build();

        classe.setTeacher(teacher);
        Classe body = classeService.save(classe);

        return (body == null) ?  ResponseEntity.badRequest().build() : ResponseEntity.created(null).body(body);
    }

    @PostMapping("/{id}/classes/{classeId}/evaluations")
    public ResponseEntity<?> createEvaluation(@PathVariable("id") Long id, @PathVariable("classeId") Long classeId, @RequestBody Evaluation evaluation) {

        Teacher teacher = teacherService.findById(id);

        if(teacher == null) return ResponseEntity.noContent().build();
        
        for (Classe classe: teacher.getClasses()) {
            if(classe.getId() == classeId) {
                evaluation.setClasse(classe);
              //  evaluation.setStatus(Status.CLOSED);
                Evaluation body = evaluationService.save(evaluation);

                if (body == null) return ResponseEntity.internalServerError().build();

                return  ResponseEntity.created(null).body(body);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/classes/{classeId}/evaluations/{evaluationId}/questionnaires")
    public ResponseEntity<?> createQuestionnaire(@PathVariable("id") Long id, @PathVariable("classeId") Long classeId, @PathVariable("evaluationId") Long evaluationId, @RequestBody Questionnaire questionnaire) {
        
        Teacher teacher = teacherService.findById(id);

        if(teacher == null) return ResponseEntity.noContent().build();
        
        for (Classe classe : teacher.getClasses()) {
            if(classe.getId() == classeId) {
                for (Evaluation evaluation : classe.getEvaluations()) {
                    if (evaluation.getId() == evaluationId) {
                        Questionnaire body = questionnaireService.create(questionnaire,evaluationId); 
                        
                        return (body == null) ? ResponseEntity.internalServerError().build() : ResponseEntity.created(null).body(questionnaire);
                    }
                }
                
            }
        
        }

        return ResponseEntity.noContent().build(); 
    }


    @PutMapping("/{id}/classes/{classeId}")
    public Classe update(@RequestBody Classe classe) {
        return classeService.save(classe);
    }

    @PutMapping("/{id}")
    public Teacher update (@RequestBody Teacher teacher)  {
        return teacherService.save(teacher);
    }

    @GetMapping("")
    public List<Teacher> getTeacher() {
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public Teacher read(@PathVariable("id") Long id) {
        return teacherService.findById(id);
    }

    @GetMapping("/{classeId}/evaluations")
    public ResponseEntity<?> readEvaluations(@PathVariable("classeId") Long classeId) {
       
        Classe classe = classeService.findById(classeId);
        
        if(classe == null) return ResponseEntity.noContent().build();

        List<Evaluation> body = new ArrayList<>();
        body = evaluationService.findEvaluations(classe);

        if (body == null) return ResponseEntity.badRequest().build();
       
        return ResponseEntity.ok(body);
        
    }

    @PostMapping("/{id}/student/{studentId}/assignment/{assignmentId}/")
    public ResponseEntity<?> sendCopyForAStudent(
            @PathVariable("id") Long id,
            @PathVariable("studentId") Long studentId,
            @PathVariable("assignmentId") Long assignmentId,
            @RequestBody Copy copy) {


       // Teacher teacher = teacherService.findById(id);

        Student student = studentService.findById(studentId);
        Assignment assignment = assignmentService.findById(assignmentId);

        if(student == null) return ResponseEntity.noContent().build();

        for (Classe classe: student.getClasses()) {
            if(classe.getId() == assignment.getClasse().getId()) {
                copy.setAssignment(assignment);
                copy.setStudent(student);
                //  evaluation.setStatus(Status.CLOSED);
                Copy body = copyService.sendCorrectedCopy(copy);

                if (body == null) return ResponseEntity.internalServerError().build();

                return  ResponseEntity.created(null).body(body);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/assignment/{id}")
    public List<Copy> findCopiesByAssignment(@PathVariable("id") Long id) {

        try {
            return copyService.findByAssignment(id);
        }
        catch (Exception e) {
            return null;
        }
    }
    @GetMapping("/student/{id}")
    public List<Copy> findCopiesByStudent(@PathVariable("id") Long id) {
        try {
            return copyService.findByStudent(id);
        }
        catch (Exception e) {
            return null;
        }
    }
}
