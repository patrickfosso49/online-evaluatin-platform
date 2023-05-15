package com.projet.classwork.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Teacher extends User {
   
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = CascadeType.MERGE)
    private List<Classe> classes = new ArrayList<>();

}
