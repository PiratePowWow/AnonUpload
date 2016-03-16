package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PiratePowWow on 3/16/16.
 */
@RestController
public class AnonUploadController {
    Server dbui = null;
    @PostConstruct
    public void init() throws SQLException {
        dbui = Server.createWebServer().start();
    }
    @PreDestroy
    public void destroy(){
        dbui.stop();
    }
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, Boolean isPerm, String comment) throws IOException {
        if(isPerm==null){
            isPerm = false;
        }
        File dir = new File("public/files");
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());
        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), isPerm, Timestamp.valueOf(LocalDateTime.now()), comment);
        files.save(anonFile);
        response.sendRedirect("/");
    }
    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<AnonFile> getFiles() throws IOException {
        ArrayList<AnonFile> filesOnTheChoppingBlock = (ArrayList) files.findByIsPermFalseOrderByTimestampDesc();
        int numNonPermFiles = filesOnTheChoppingBlock.size();
        if(numNonPermFiles > 5){
            for(int i = 5;i < numNonPermFiles; i++){
                AnonFile fileAboutToBeDeleted = filesOnTheChoppingBlock.get(i);
                files.delete(fileAboutToBeDeleted);
                Files.delete(FileSystems.getDefault().getPath("public/files", fileAboutToBeDeleted.getFilename()));
            }
        }
        return (List<AnonFile>) files.findAll();
    }
}

