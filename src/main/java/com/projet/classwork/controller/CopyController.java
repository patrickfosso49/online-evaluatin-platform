package com.projet.classwork.controller;

import com.projet.classwork.model.Assignment;
import com.projet.classwork.model.Copy;
import com.projet.classwork.service.CopyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/copy")
public class CopyController {

    private CopyService copyService;

    public CopyController(CopyService copyService){
            this.copyService = copyService;
    }

    @GetMapping("/")
    public List<Copy> findAll() {
        try {
            return copyService.findAll();
        }
        catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Copy findById(@PathVariable("id") Long id) {

        return copyService.findById(id);
    }

    @GetMapping("/assignment/{id}")
    public List<Copy> findCopyByAssignment(@PathVariable("id") Long id) {

        try {
            return copyService.findByAssignment(id);
        }
        catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/student/{id}")
    public List<Copy> findCopyByStudent(@PathVariable("id") Long id) {
        try {
            return copyService.findByStudent(id);
        }
        catch (Exception e) {
            return null;
        }
    }

    @PostMapping(value="/")
    public Copy createEvaluation(@RequestBody Copy copy) {
        return copyService.save(copy);
    }


}
