package com.projet.classwork.Services;
import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Teacher;
import java.util.List;

public interface TeacherFunctions {

    List<Teacher> getAllTeachersWithClasses();
    Teacher addTeacherWithClass(Teacher teacher, Classe classe);
     void deleteById(Long id);
    Teacher updateTeacher(Long id, Teacher teacherDetails);
    
}
