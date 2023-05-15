package com.projet.classwork.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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


    @OneToMany(mappedBy = "classe")
    private List<Evaluation> evaluations;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @JsonIgnore
    @ManyToMany(mappedBy = "classes")
    private List<Student> students;
    
    public void addStudent(Student student) {
        students.add(student);
        student.getClasses().add(this);
    }

    public void removeStudent (Student student) {
        students.remove(student);
        student.getClasses().remove(this);
    }

    @Override
    public String toString() {
        return null;
    }

    public void addEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

}
