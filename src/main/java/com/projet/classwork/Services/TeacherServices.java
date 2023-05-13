package com.projet.classwork.Services;

import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Teacher;
import com.projet.classwork.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServices implements TeacherFunctions {

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherServices(TeacherRepository Repository) {
        teacherRepository = Repository;
    }

    public List<Teacher> getAllTeachersWithClasses() {
        try {
            return teacherRepository.findAll();
        } catch (Exception e) {
            return null;
        }

    }

    public Teacher addTeacherWithClass(Teacher teacher, Classe classe) {
        try {

            teacher.getClasses().add(classe);
            return teacherRepository.save(teacher);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setRegistrationNumber(teacherDetails.getRegistrationNumber());
        teacher.setEmail(teacherDetails.getEmail());
        return teacherRepository.save(teacher);
    }


}



