package com.example.todoapp.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.Models.HealthResponse;

@RestController
@RequestMapping("/api/v1/health")
public class HealthV1Controller {
    @GetMapping
    public ResponseEntity<HealthResponse> getHealth() {
        String myResponse = "Api is working on port 8080 !";

        HealthResponse healthResponse = new HealthResponse(myResponse);


        return ResponseEntity.ok(healthResponse);
    }
}
