package com.projet.classwork.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Teacher extends User {
   
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher", cascade = CascadeType.MERGE)
    private Collection<Class> classes = new ArrayList<>();

}
