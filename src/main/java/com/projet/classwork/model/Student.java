package com.projet.classwork.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Student extends User{

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_classe",
        joinColumns = @JoinColumn(
            name = "student_id", referencedColumnName = "id"
        ) ,
        inverseJoinColumns = @JoinColumn(
            name = "class_id", referencedColumnName = "id"
        )
    )
    private List<Classe> classes;

    @OneToMany(mappedBy = "student")
    private List<Submission> submissions;
    
}
