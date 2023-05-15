package com.projet.classwork.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.model.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionDto {

    private Long id;
    private Float mark;
    private LocalDateTime time;

    private List<PropositionDto> propositionDtos;

}
