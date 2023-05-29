package com.projet.classwork.service;

import com.projet.classwork.model.Assignment;
import com.projet.classwork.model.Copy;
import com.projet.classwork.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CopyService {

    private CopyRepository copyRepository;

    @Autowired
    GoogleDriveService driveService;

    public CopyService(CopyRepository copyRepository){
        this.copyRepository = copyRepository;
    }

    public Copy findById(Long id){
        try {
            return copyRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }


    public Copy save(Copy copy) {
        try {
            // initially, the link is the folder's path to the user's local device
            String filePath = copy.getLink();
            String folderName = copy.getAssignment().getTitle();
            String driveLink = uploadFileToDrive(filePath, folderName);
            System.out.println("link drive === "+driveLink);
            copy.setLink(driveLink);
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
            System.out.println(driveFile.getWebContentLink());
            return driveFile.getWebContentLink();
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
