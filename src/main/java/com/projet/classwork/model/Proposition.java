package com.projet.classwork.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.classwork.dto.PropositionDto;
import com.projet.classwork.repository.PropositionRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proposition {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private List<String> values;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Submission submission;

}
