package com.projet.classwork.controller;

import com.projet.classwork.model.*;
import com.projet.classwork.service.EvaluationService;
import com.projet.classwork.service.QuestionService;
import com.projet.classwork.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionService questionService;

    @PostMapping("/")
    public Evaluation saveEvaluation(
             @RequestBody Evaluation evaluation
    ) {
        return evaluationService.saveEvaluation(evaluation);
    }

    @GetMapping("/")
    public List<Evaluation> listEvaluation(){
        return evaluationService.fetchAllEvaluation();
    }

   /*@PutMapping("/updateEvaluation/{id}")
    public Evaluation updateEvaluation(@RequestBody Evaluation evaluation,
                     @PathVariable("id") Long evaluationID)
    {
        return evaluationService.updateEvaluation(
                evaluation, evaluationID);
    }*/

    @DeleteMapping("/delete/{id}")
    public String deleteEvaluation(@PathVariable("id")
                                       Long evaluationID)
    {
        evaluationService.deleteEvaluationById(
                evaluationID);
        return "Deleted Successfully";
    }

    @GetMapping("/listQuestionnaires")
    public List<Questionnaire> listQuestionnaires(){
        return questionnaireService.fetchAllEQuestionnaires();
    }

    @DeleteMapping("/deleteQuestionnaires/{id}")
    public String deleteQuestionnaire(
            @PathVariable("id")
            Long questionnaireID
    ){
        questionnaireService.deleteQuestionnaireById(questionnaireID);
        return "questionnaire deleted successfully";
    }

    @GetMapping("/Questionnaire/question/{id}")
    public Optional<Question> getQuestion(
            @PathVariable("id")
            Long questionID
    ){
        return questionService.findQuestionById(questionID);
    }

}
