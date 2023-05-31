package com.projet.classwork.controller;


import com.projet.classwork.model.Assignment;
import com.projet.classwork.service.AssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignment")
public class AssignmentController {

    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService){
        this.assignmentService = assignmentService;
    }

    @GetMapping("/")
    public List<Assignment> findAll() {
        try {
            return assignmentService.findAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Assignment findById(@PathVariable("id") Long id) {

        return assignmentService.findById(id);
    }

    @GetMapping("/classe/{id}")
    public List<Assignment> findAssignmentByClasse(@PathVariable("id") Long id) {

        try {
            return assignmentService.findByClasse(id);
        }
        catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value="/")
    public Assignment createEvaluation(@RequestBody Assignment assignment) {
        return assignmentService.save(assignment);
    }
}
