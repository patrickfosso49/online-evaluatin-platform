package com.projet.classwork.controller;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.model.Submission;
import com.projet.classwork.service.PropositionService;
import com.projet.classwork.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("proposition")
public class PropositionController {

    @Autowired
    private PropositionService propositionService;

    @PostMapping("/save")
    public Proposition saveProposition(
            @Validated @RequestBody Proposition proposition){
        return propositionService.saveProposition(proposition);
    }

    @GetMapping("/list")
    public List<Proposition> listProposition(){
        return propositionService.fetchAllProposition();
    }

    @GetMapping("/get/{id}")
    public Optional<Proposition> getPropositionByID(
            @PathVariable("id") Long propositionID){
        return propositionService.findPropositionById(propositionID);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProposition(@PathVariable("id")
                                   Long propositionID)
    {
        propositionService.deletePropositionById(
                propositionID);
        return "Deleted Successfully";
    }

}
