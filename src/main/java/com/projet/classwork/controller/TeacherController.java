package com.projet.classwork.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.model.Teacher;
import com.projet.classwork.service.ClasseService;
import com.projet.classwork.service.EvaluationService;
import com.projet.classwork.service.QuestionnaireService;
import com.projet.classwork.service.TeacherService;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
   
    
    private final ClasseService classeService;
    private final TeacherService teacherService;
    private final EvaluationService evaluationService;
    private final QuestionnaireService questionnaireService;

    public TeacherController( TeacherService teacherService, ClasseService classeService,
        EvaluationService evaluationService, QuestionnaireService questionnaireService) {
            this.teacherService = teacherService;
            this.classeService = classeService;
            this.evaluationService = evaluationService;
            this.questionnaireService = questionnaireService;
    }


    @PostMapping("")
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

   
}
