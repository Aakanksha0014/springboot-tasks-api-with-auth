package com.example.tasksapi.controller;

import com.example.tasksapi.dto.AuthRequest;
import com.example.tasksapi.dto.AuthResponse;
import com.example.tasksapi.model.User;
import com.example.tasksapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest req) {
        User u = authService.register(req.getUsername(), req.getPassword());
        return new ResponseEntity<>("User registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        String token = authService.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
