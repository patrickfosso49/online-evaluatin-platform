package com.projet.classwork.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.model.Question;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDto {

    private Questionnaire questionnaire;
    private List<Question> questions;

}
