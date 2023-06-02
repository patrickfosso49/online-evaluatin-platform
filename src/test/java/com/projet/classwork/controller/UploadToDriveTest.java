package com.projet.classwork.controller;

import com.projet.classwork.service.GoogleDriveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class UploadToDriveTest {

    @Autowired
    GoogleDriveService googleDriveService;

    @Test
    public void testUpload(){
        File file = new File("C:\\Users\\Perrin Njietche\\Desktop\\td2.pdf");
    }


}
