package com.projet.classwork.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Teacher;
import com.projet.classwork.repository.ClasseRepository;
import com.projet.classwork.repository.TeacherRepository;

@SpringBootTest
public class ClassControllerTest {
   
    @Autowired private ClasseRepository classRepository;

    @Autowired private TeacherRepository teacherRepository;

    @Test
    public void saveClassTest() {


        Classe class1 = new Classe(null, "INFO L2", null, null); 

        Assertions.assertNotNull(classRepository.save(class1));
    }
}
