package com.projet.classwork.service;

import com.projet.classwork.model.Assignment;
import com.projet.classwork.model.Copy;
import com.projet.classwork.model.Student;
import com.projet.classwork.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CopyService {

    private CopyRepository copyRepository;
    private final StudentService studentService;
    private final AssignmentService assignmentService;


    @Autowired
    GoogleDriveService driveService;

    public CopyService(CopyRepository copyRepository, StudentService studentService, AssignmentService assignmentService){
        this.copyRepository = copyRepository;
        this.studentService = studentService;
        this.assignmentService = assignmentService;
    }

    public Copy findById(Long id){
        try {
            return copyRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }


    public Copy createStudentCopy(Copy copy, Long studentId, Long assignmentId) {
        try {
            // initially, the link is the folder's path to the user's local device
            System.out.println("Here is the copy 1; "+copy.toString());
            String filePath = copy.getLinkSent();
            System.out.println("Here is the copy; 2"+copy.toString());
            String driveLink = uploadFileToDrive(filePath, "Classwork_Copies");
            System.out.println("link drive === "+driveLink);
            Assignment assignment = assignmentService.findById(assignmentId);
            Student student = studentService.findById(studentId);
            copy.setAssignment(assignment);
            copy.setStudent(student);
            copy.setLinkSent(driveLink);

            return copyRepository.save(copy);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Copy sendCorrectedCopy(Copy copy) {
        try {
            // initially, the link is the folder's path to the user's local device
            String filePath = copy.getLinkSent();
            String driveLink = uploadFileToDrive(filePath, "Classwork_Copies_Corrected");
            System.out.println("link drive === "+driveLink);
            copy.setLinkCorrected(driveLink);
            return copyRepository.save(copy);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String uploadFileToDrive(String filePath, String folderName){
        File file = new File(filePath);
        com.google.api.services.drive.model.File driveFile = driveService.uploadFileToFolder(file.getName(), file.getAbsolutePath(), "application/pdf", folderName);
        try {
            System.out.println(driveFile.getWebViewLink());
            return driveFile.getWebViewLink();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public List<Copy> findAll(){
        try{
            return copyRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public List<Copy> findByAssignment(Long id){
        try{
            return copyRepository.findCopiesByAssignmentId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Copy> findByStudent(Long id){
        try{
            return copyRepository.findCopiesByStudentId(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
