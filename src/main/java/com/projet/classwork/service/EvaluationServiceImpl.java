package com.projet.classwork.service;

import com.projet.classwork.model.Answer;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.model.Question;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.repository.evaluation.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EvaluationServiceImpl implements EvaluationService{

    @Autowired
    EvaluationRepository evaluationRepo;

    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepo.save(evaluation);
    }

    @Override
    public Optional<Evaluation> findEvaluationById(Long id) {
        return evaluationRepo.findById(id);
    }

    @Override
    public List<Evaluation> fetchAllEvaluation() {
        return evaluationRepo.findAll();
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation, Long evaluationId) {

        Evaluation evaluationToUpdate = evaluationRepo.findById(evaluationId).get();

        if(Objects.nonNull(evaluation.getTime()) &&
                !"".equalsIgnoreCase(evaluation.getTime().toString())){
            evaluationToUpdate.setTime(evaluation.getTime());
        }

        if(Objects.nonNull(evaluation.getTitle()) &&
                !"".equalsIgnoreCase(evaluation.getTitle().toString())){
            evaluationToUpdate.setTitle(evaluation.getTitle());
        }

        if(Objects.nonNull(evaluation.getInstructions()) &&
                !"".equalsIgnoreCase(evaluation.getInstructions().toString())){
            evaluationToUpdate.setInstructions(evaluation.getInstructions());

        }

        if(Objects.nonNull(evaluation.getClasse()) &&
                !"".equalsIgnoreCase(evaluation.getClasse().toString())){
            evaluationToUpdate.setClasse(evaluation.getClasse());
        }

       if(Objects.nonNull(evaluation.getExpiration()) &&
                !"".equalsIgnoreCase(evaluation.getExpiration().toString())){
            evaluationToUpdate.setExpiration(evaluation.getExpiration());
        }

        if(Objects.nonNull(evaluation.getQuestionnaire()) &&
                !"".equalsIgnoreCase(evaluation.getQuestionnaire().toString())){
            evaluationToUpdate.setQuestionnaire(updateQuestionnaire(evaluationToUpdate.getQuestionnaire(), evaluation.getQuestionnaire()));
        }

        evaluationToUpdate.setQuestionnaire(updateQuestionnaire(evaluationToUpdate.getQuestionnaire(), evaluation.getQuestionnaire()));


        return evaluationRepo.save(evaluationToUpdate);
    }

    public Questionnaire updateQuestionnaire(Questionnaire questionnaireToUpdate, Questionnaire questionnaire){
        if(Objects.nonNull(questionnaire.getDuration()+"") &&
                !"".equalsIgnoreCase(questionnaire.getDuration()+"")){
            questionnaireToUpdate.setDuration(questionnaire.getDuration());
        }else{
            return questionnaire;//not updated
        }
        return questionnaireToUpdate;
    }
    public List<Question> updateQuestionList(List<Question> listQuestionToUpdate, List<Question> listQuestion){
        if(listQuestionToUpdate.size() != listQuestion.size()){
            return listQuestion;//no update performed
        }
        for(int i = 0; i < listQuestionToUpdate.size(); i++){
            listQuestionToUpdate.set(i, updateQuestion(listQuestionToUpdate.get(i), listQuestion.get(i)));
        }
        return  listQuestionToUpdate;
    }
    public Question updateQuestion(Question questionToUpdate, Question question){
        if(Objects.nonNull(question.getDuration()+"") &&
                !"".equalsIgnoreCase(question.getDuration()+"")){
            questionToUpdate.setDuration(question.getDuration());
        }else{
            return question;
        }

        questionToUpdate.setAnswer(updateAnswer(questionToUpdate.getAnswer(), question.getAnswer()));

        questionToUpdate.setChoices(updateQuestionChoices(questionToUpdate.getChoices(), question.getChoices()));

        if(Objects.nonNull(question.getStatement()) &&
                !"".equalsIgnoreCase(question.getStatement())){
            questionToUpdate.setStatement(question.getStatement());
        }else{
            return question;
        }
        questionToUpdate.setQuestionnaire(question.getQuestionnaire());
        return questionToUpdate;
    }


    public List<String> updateQuestionChoices(List<String> choicesToUpdate, List<String> choices){
        List<String> oldValues = choicesToUpdate;
        List<String> newValues = choices;
        if (oldValues.size() != newValues.size()) {
            return choices;// TODO: No update performed could've returned null
        }
        for (int i = 0; i < oldValues.size(); i++) {
            oldValues.set(i, newValues.get(i));
        }
        return oldValues;
    }

    public Answer updateAnswer(Answer answerToUpdate, Answer answer) {
        if (Objects.nonNull(answer.getValues()) && !answer.getValues().isEmpty()) {
            List<String> oldValues = answerToUpdate.getValues();
            List<String> newValues = answer.getValues();
            if (oldValues.size() != newValues.size()) { // answers need to be of same size
                return answer;
            }

            answerToUpdate.setValues(newValues);
            return answerToUpdate;
        }
        return answer;
    }
    @Override
    public void deleteEvaluationById(Long Id) {
         evaluationRepo.deleteById(Id);
    }
}
