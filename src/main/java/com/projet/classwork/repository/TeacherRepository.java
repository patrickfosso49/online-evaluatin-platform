package com.projet.classwork.repository;


import com.projet.classwork.model.Teacher;

import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends UserRepository<Teacher> {

   
     Teacher findByEmail(String email);

     boolean existsByEmail(String email);

    boolean existsByRegistrationNumber(String registrationNumber);

}
