package com.projet.classwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.classwork.model.Student;
import com.projet.classwork.model.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByStudent(Student student);
    
}
