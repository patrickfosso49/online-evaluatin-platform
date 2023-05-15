package com.projet.classwork.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Submission {
   
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Float mark;

    @Column(nullable = false)
    private LocalDateTime time;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "submission", cascade = {CascadeType.MERGE})
    List<Proposition> propositions;

}
