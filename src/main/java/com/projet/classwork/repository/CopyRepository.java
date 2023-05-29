package com.projet.classwork.repository;

import com.projet.classwork.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Long> {
    List<Copy> findCopiesByAssignmentId(Long id);
    List<Copy> findCopiesByStudentId(Long id);
}
