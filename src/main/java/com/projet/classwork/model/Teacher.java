package com.projet.classwork.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Teacher extends User {
   

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private Collection<Class> classes;
}
