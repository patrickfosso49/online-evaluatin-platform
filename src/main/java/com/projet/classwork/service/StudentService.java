package com.projet.classwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.classwork.model.Student;
import com.projet.classwork.repository.StudentRepository;

@Service
public class StudentService {
   
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student findById(Long id) {
        try {
            return studentRepository.findById(id).get();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> findAll() {
        try {
            return studentRepository.findAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Student save(Student student) {
        
        try {
           return studentRepository.save(student);
        }
        catch (Exception e) {
            return null;
        }
    }
}
