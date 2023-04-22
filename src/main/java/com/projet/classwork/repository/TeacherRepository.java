package com.projet.classwork.repository;


import com.projet.classwork.model.Teacher;

public interface TeacherRepository extends UserRepository<Teacher> {

   
    public Teacher findByEmail(String email);

    public boolean existsByEmail(String email);

    public boolean existsByRegistrationNumber(String registrationNumber);
    
}
