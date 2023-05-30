package com.projet.classwork.controller;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.service.PropositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/proposition")
public class PropositionController {

    @Autowired
    private PropositionService propositionService;

    @PostMapping("/")
    public Proposition saveProposition(
             @RequestBody Proposition proposition){
        return propositionService.saveProposition(proposition);
    }

    @GetMapping("/")
    public List<Proposition> listProposition(){
        return propositionService.fetchAllProposition();
    }

    @GetMapping("/{id}")
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
