package com.projet.classwork.service;

import com.projet.classwork.model.Assignment;
import com.projet.classwork.model.Evaluation;
import com.projet.classwork.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment findById(Long id) {

        try {
            return assignmentRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Assignment save(Assignment assignment) {

        try {
            return assignmentRepository.save(assignment);
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Assignment> findAll() {

        try {
            return assignmentRepository.findAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Assignment> findByClasse(Long id){
        try {
            return assignmentRepository.findAssignmentsByClasse_Id(id);
        }
        catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
