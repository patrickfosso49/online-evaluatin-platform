package com.projet.classwork.controller;

import com.projet.classwork.model.Submission;
import com.projet.classwork.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/save")
    public Submission saveSubmission(
            @Validated @RequestBody Submission submission){
        return submissionService.save(submission);
    }

    @GetMapping("/list")
    public List<Submission> listSubmission(){
        return submissionService.fetchAllSubmission();
    }

    @GetMapping("/get/{id}")
    public Optional<Submission> getSubmissionByID(
            @PathVariable("id") Long submissionId){
        return submissionService.findSubmissionByID(submissionId);
    }

    @PostMapping("/correctSubmission")
    public Submission getSubmissionCorrection( @Validated @RequestBody Submission submission){
        return submissionService.correctSubmission(submission);
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
