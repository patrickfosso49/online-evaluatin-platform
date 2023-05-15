package com.projet.classwork.repository;


import com.projet.classwork.model.Teacher;


public interface TeacherRepository extends UserRepository<Teacher> {

    @Override 
    public Teacher findByEmail(String email);

    @Override
    public boolean existsByEmail(String email);

    @Override
    public boolean existsByRegistrationNumber(String registrationNumber);

}
