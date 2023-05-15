package com.projet.classwork.controller;

import java.util.Collection;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projet.classwork.repository.TeacherRepository;
import com.projet.classwork.model.Teacher;
import com.projet.classwork.model.Classe;

@SpringBootTest
public class TeacherControllerTest {
    
    @Autowired 
    private TeacherRepository teacherRepository;


    @Test
    public void testGetTeachers() {

        Assertions.assertNotNull(teacherRepository.findByEmail("johndoe@gmail.com"));
    }

    @Test
    public void testSaveTeachers() {

        //Collection<Class> classes = new ArrayList<>();

        //Class class1 = new Class(2L, "INFO L1", null, null);


        Teacher teacher = new Teacher();
        //class1.setTeacher(teacher);
        //teacher.setClasses(classes);
        teacher.setFirstName("dennis");
        teacher.setLastName("joliot");
        teacher.setEmail("dennisjoliot@gmail.com");
        teacher.setRegistrationNumber("I3F8CF");
        teacher.setPassword("s!edf.srs!__e_");
        
        
        Teacher mock = teacherRepository.save(teacher);

        Assertions.assertNotNull(mock);
        
    }
}

