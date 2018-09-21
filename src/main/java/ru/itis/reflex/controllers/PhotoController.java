package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.FPAnalyzerService;
import ru.itis.reflex.services.interfaces.FPCacheService;

import java.io.IOException;

@RestController
public class PhotoController {

    @Autowired
    FPAnalyzerService fpAnalyzerService;

    @Autowired
    FPCacheService fpCacheService;

    @PostMapping("/initialize_following")
    public ResponseEntity<?> initializeFollowing(@RequestParam MultipartFile image, User user) throws IOException {
        if (image.isEmpty()) {
            return new ResponseEntity<>("Error", HttpStatus.OK);
        } else {
            //TODO ПОЛУЧЕНИЕ ЮЗЕРА
            fpAnalyzerService.initialize(user, image.getBytes());
        }

        return new ResponseEntity<>("Successfully uploaded - " +
                image.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping("/update_user_position")
    public ResponseEntity<?> updateUserPosition(@RequestParam MultipartFile image, User user) throws IOException {
        if (image.isEmpty()) {
            return new ResponseEntity<>("Error", HttpStatus.OK);
        } else {
            fpAnalyzerService.update(user, image.getBytes());
        }
        return new ResponseEntity<Object>("Updated", HttpStatus.OK);
    }

    @GetMapping("/check_flex")
    public ResponseEntity checkUserFlex(User user) {
        if (fpAnalyzerService.isFlexing(user)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/check_tiredness")
    public ResponseEntity checkUserForTiredness(User user) {
        if(fpAnalyzerService.isInitialized(user)){
            return ResponseEntity.ok(fpAnalyzerService.isTired(user));
        }
        return ResponseEntity.ok(false);
    }











}
