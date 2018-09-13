package ru.itis.reflex;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class HomeController {



    @PostMapping("/")
    public ResponseEntity<?> getPhoto(
            @RequestParam MultipartFile myImage) throws IOException {

        String name = myImage.getOriginalFilename();
        System.out.println("File name: "+name);

        if (myImage.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        //                saveUploadedFiles(Arrays.asList(uploadfile));

        return new ResponseEntity("Successfully uploaded - " +
                myImage.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

}
