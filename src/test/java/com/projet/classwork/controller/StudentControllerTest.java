package com.projet.classwork.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projet.classwork.model.Student;
import com.projet.classwork.repository.StudentRepository;

@SpringBootTest
public class StudentControllerTest {
   
    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void saveStudentTest () {

        Student student = new Student(null);
        student.setEmail("patrickfosso49@gmail.com");
        student.setLastName("fosso");
        student.setPassword("jqmdk_");
        student.setRegistrationNumber("3A3EZE");
        student.setFirstName("patrick");
        
        Assertions.assertNotNull(studentRepository.save(student));
    }
    
}
