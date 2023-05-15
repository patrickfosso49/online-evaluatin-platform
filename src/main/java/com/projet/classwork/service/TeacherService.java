package com.projet.classwork.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.classwork.model.Teacher;
import com.projet.classwork.repository.TeacherRepository;

@Service
public class TeacherService {
   
    
    private final TeacherRepository teacherRepository;


    public TeacherService (TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        try {
            return teacherRepository.findAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Teacher findById(Long id) {
        try {
            return teacherRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Teacher save(Teacher teacher) {
        try {
            return teacherRepository.save(teacher);
        }
        catch (Exception e) {
            return null;
        }
    }
}
