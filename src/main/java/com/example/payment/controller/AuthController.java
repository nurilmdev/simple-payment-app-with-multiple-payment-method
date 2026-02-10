package com.example.payment.controller;

import com.example.payment.dto.AuthRequest;
import com.example.payment.security.JwtService;
import com.example.payment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        // dummy validation dulu
//        if (!request.getUsername().equals("admin")
//                || !request.getPassword().equals("admin123")) {
//            throw new RuntimeException("Invalid credentials");
//        }
     return authService.login(request);
    }
}
