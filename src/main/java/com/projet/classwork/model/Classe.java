package com.projet.classwork.model;

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
public class Classe {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(unique = true, nullable = false, length = 80)
    private String name;

    
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Evaluation> evaluations;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;


    @Override
    public String toString() {
        return null;
    }
}
