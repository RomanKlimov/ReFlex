package ru.itis.reflex.controllers;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class HomeController {
    @PostMapping("/")
    public ResponseEntity<?> getPhoto(
            @RequestParam MultipartFile myImage) throws IOException {
        byte[] bytes = myImage.getBytes();
        nu.pattern.OpenCV.loadShared();
        Mat mat = Imgcodecs.imdecode(new MatOfByte(bytes), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

        if (myImage.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        //                saveUploadedFiles(Arrays.asList(uploadfile));

        return new ResponseEntity("Successfully uploaded - " +
                myImage.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

}
