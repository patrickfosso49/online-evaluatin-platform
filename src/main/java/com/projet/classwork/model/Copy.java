package com.projet.classwork.model;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = true, unique = true)
    private String linkSent;

    @Column(nullable = true, unique = true)
    private String linkCorrected;

    @Column(nullable = false)
    private LocalDateTime dateSent;

    @JsonIgnore
    @ManyToOne
    private Assignment assignment;

    @JsonIgnore
    @ManyToOne
    private Student student; 


}
