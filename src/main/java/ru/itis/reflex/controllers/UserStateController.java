package ru.itis.reflex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.reflex.models.User;
import ru.itis.reflex.services.interfaces.AuthService;
import ru.itis.reflex.services.interfaces.FPAnalyzerService;
import ru.itis.reflex.services.interfaces.FPCacheService;

import java.io.IOException;

@RestController
public class UserStateController {

    @Autowired
    private AuthService authService;

    @Autowired
    FPAnalyzerService fpAnalyzerService;

    @Autowired
    FPCacheService fpCacheService;

    @PostMapping("/initialize_fp")
    public ResponseEntity<?> initializeFollowing(@RequestParam MultipartFile myImage, Authentication authentication) throws IOException {
        User user = authService.getUserByAuthentication(authentication);
        if (myImage.isEmpty()) {
            return new ResponseEntity<>("Error", HttpStatus.OK);
        } else {
            fpAnalyzerService.initialize(user, myImage.getBytes());
        }

        System.out.println("init" + fpCacheService.toString());

        return new ResponseEntity<>("Successfully uploaded - " +
                myImage.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping("/update_fp")
    public ResponseEntity updateUserPosition(@RequestParam MultipartFile myImage,  Authentication authentication) throws IOException {
        User user = authService.getUserByAuthentication(authentication);
        if (myImage.isEmpty()) {
            return new ResponseEntity<>("Error", HttpStatus.OK);
        } else {
            boolean[] states = fpAnalyzerService.update(user, myImage.getBytes());
            System.out.println("update " + fpCacheService.toString());
            return ResponseEntity.ok(states);
        }
    }

    @GetMapping("/check_flex")
    public ResponseEntity checkUserFlex(Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        System.out.println("check_flex " + fpCacheService.toString());
        if (fpAnalyzerService.isFlexing(user)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/check_tiredness")
    public ResponseEntity checkUserForTiredness(Authentication authentication) {
        User user = authService.getUserByAuthentication(authentication);
        System.out.println("check tiredness " + fpCacheService.toString());
        if(fpAnalyzerService.isInitialized(user)){
            return ResponseEntity.ok(fpAnalyzerService.isTired(user));
        }
        return ResponseEntity.ok(false);
    }

    @GetMapping("/end_user_following_session")
    public ResponseEntity endUserFollowingSession(Authentication authentication) {
        System.out.println("end1" + fpCacheService.toString());
        User user = authService.getUserByAuthentication(authentication);
        fpCacheService.deleteUserFromCache(user);
        System.out.println("end2" + fpCacheService.toString());
        return (ResponseEntity) ResponseEntity.ok();
    }

}
