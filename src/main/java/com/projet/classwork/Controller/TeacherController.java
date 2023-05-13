package com.projet.classwork.Controller;

import com.projet.classwork.Services.TeacherFunctions;
//import com.projet.classwork.Services.TeacherServices;
import com.projet.classwork.model.Classe;
import com.projet.classwork.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("Teacher")
public class TeacherController {

    @Autowired
    private TeacherFunctions eservice;

    public TeacherController(TeacherFunctions teacherFunctions){
        eservice=teacherFunctions;
    }

    @GetMapping("/listTeacher")
    public List<Teacher> getAllTeachersWithClasses(){
        System.out.println("reussit");
        return eservice.getAllTeachersWithClasses();
    }

    @PostMapping("/addTeacherAndclasse")
    public Teacher addTeacherWithClass(@RequestBody Teacher teacher, @RequestBody Classe classe) {
        return eservice.addTeacherWithClass(teacher, classe);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        eservice.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
        return eservice.updateTeacher(id, teacherDetails);
    }

}



