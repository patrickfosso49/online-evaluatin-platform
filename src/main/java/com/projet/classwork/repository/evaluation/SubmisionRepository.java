package com.projet.classwork.repository.evaluation;

import com.projet.classwork.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmisionRepository extends JpaRepository<Submission, Long> { }
