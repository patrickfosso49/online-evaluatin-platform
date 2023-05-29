package com.projet.classwork.repository;

import com.projet.classwork.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAssignmentsByClasse_Id(Long classe_id);
}
