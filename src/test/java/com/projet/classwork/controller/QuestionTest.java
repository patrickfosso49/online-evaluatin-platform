package com.projet.classwork.controller;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projet.classwork.model.Answer;
import com.projet.classwork.model.Question;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.repository.QuestionRepository;
import com.projet.classwork.repository.QuestionnaireRepository;


@SpringBootTest 
public class QuestionTest {
   
   @Autowired  QuestionnaireRepository questionnaireRepository;
    
    @Autowired private QuestionRepository questionRepository;

    @Test
    public void saveQuestionTest() {

        Questionnaire questionnaire = questionnaireRepository.findById(1L).get();

        List<String> answers = new ArrayList<>();
   
        List<String> choices = new ArrayList<>();

        choices.add("a program");

        choices.add("instance of a program");

        choices.add("instance of a running program");
        

        answers.add("instance of a running program");

        Answer ans = new Answer(null, answers);

        Question question = new Question(null, "what's the last name of lelouch in code geass", choices, 30, ans, questionnaire);

        question.setAnswer(ans);

        Assertions.assertNotNull(questionRepository.save(question));

        


    }


}
