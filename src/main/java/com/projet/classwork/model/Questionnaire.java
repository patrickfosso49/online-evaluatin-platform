package com.projet.classwork.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Questionnaire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int duration;

    @JsonIgnore
    @OneToOne
    private Evaluation evaluation;

    @OneToMany(mappedBy = "questionnaire")
    private List<Submission> submissions;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.MERGE)
    private List<Question> questions;



}
