package com.example.payment.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "admin123";
        String hash = encoder.encode(rawPassword);

        System.out.println("BCrypt hash:");
        System.out.println(hash);
    }
}
