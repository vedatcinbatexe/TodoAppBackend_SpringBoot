package com.example.todoapp.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.Services.UserPrincipal;

@RestController
@RequestMapping("/api/v1/protected")
public class ProtectedController {

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        return ResponseEntity.ok()
            .body("Hello " + userPrincipal.getFirstName() + " " + userPrincipal.getLastName() + 
                  "! This is a protected endpoint. Your email is: " + userPrincipal.getUsername());
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("This is a protected endpoint that requires JWT authentication!");
    }
}
