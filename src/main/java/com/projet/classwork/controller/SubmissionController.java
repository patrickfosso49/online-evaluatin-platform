package com.projet.classwork.controller;

import com.projet.classwork.model.Submission;
import com.projet.classwork.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/")
    public Submission saveSubmission(
            @Validated @RequestBody Submission submission){
        return submissionService.save(submission);
    }

    @GetMapping("/")
    public List<Submission> listSubmission(){
        return submissionService.fetchAllSubmission();
    }

    @GetMapping("/{id}")
    public Optional<Submission> getSubmissionByID(
            @PathVariable("id") Long submissionId){
        return submissionService.findSubmissionByID(submissionId);
    }

    @GetMapping("/correctSubmission/{id}")
    public Submission getSubmissionCorrection( @PathVariable("id")
                                                   Long submissionId){
        return submissionService.correctSubmission(submissionId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSubmission(@PathVariable("id")
                                   Long submissionId)
    {
        submissionService.deleteSubmissionById(
                submissionId);
        return "Deleted Successfully";
    }

}
