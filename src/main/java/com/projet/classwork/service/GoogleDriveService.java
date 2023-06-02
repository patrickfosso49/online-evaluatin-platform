package com.projet.classwork.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;





    @Service
    public class GoogleDriveService {


        private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveService.class);//catch runtime conditions and erris, and add to log files, that contains infos about connection and servers....

        @Value("${google.service_account_email}")
        private String serviceAccountEmail;
        @Value("${google.application_name}")
        private String applicationName;

        @Value("${google.service_account_key}")
        private String serviceAccountKey;

        @Value("${google.folder_id}")
        private String folderID;


        public Drive getDriveService() {
            Drive service = null;

            try{
                URL resource = getClass().getClassLoader().getResource(this.serviceAccountKey);
                java.io.File key = Paths.get(resource.toURI()).toFile();

                HttpTransport httpTransport = new NetHttpTransport();
                JacksonFactory jsonFactory = new JacksonFactory();

                GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport).
                        setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
                        .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                        .setServiceAccountPrivateKeyFromP12File(key).build();
                service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(applicationName).setHttpRequestInitializer(credential).build();
            }catch (Exception e ) {
                System.out.println("THE ERROR IS : "+e);
                // System.out.println("--------------------END ERROR -----------------------------");
                LOGGER.error(e.getMessage());
            }
            System.out.println("DRIVE SERVICES ================ "+service.toString());
            return service;
        }


        public File uploadFileToFolder(String filename, String filepath, String mimeType, String folderName) {
            File file = new File();

            try {
                // Get the parent folder
                File parentFolder = getDriveService().files().get(folderID).execute();

                // Check if the folderName already exists
                String query = "mimeType='application/vnd.google-apps.folder' and trashed=false and '" + parentFolder.getId() + "' in parents and name='" + folderName + "'";
                FileList files = getDriveService().files().list().setQ(query).setSpaces("drive").execute();
                File folder;
                if (files.getFiles().isEmpty()) {
                    // If the folder does not exist, create it
                    folder = new File();
                    folder.setName(folderName);
                    folder.setMimeType("application/vnd.google-apps.folder");
                    folder.setParents(Collections.singletonList(parentFolder.getId()));
                    folder = getDriveService().files().create(folder).setFields("id").execute();
                } else {
                    // If the folder exists, get its ID
                    folder = files.getFiles().get(0);
                }

                // Upload the file to the folder
                java.io.File fileUpload = new java.io.File(filepath);
                com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
                fileMetaData.setMimeType(mimeType);
                fileMetaData.setName(filename);
                fileMetaData.setParents(Collections.singletonList(folder.getId()));
                com.google.api.client.http.FileContent fileContent = new FileContent(mimeType, fileUpload);
                file = getDriveService().files().create(fileMetaData, fileContent).setFields("id,webContentLink,webViewLink").execute();

            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            return file;
        }


    }
