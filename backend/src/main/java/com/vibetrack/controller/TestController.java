package com.vibetrack.controller;

import com.vibetrack.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JwtService jwtService;

    // Endpoint pra gerar token
    @GetMapping("/generate")
    public String generateToken(@RequestParam String email) {
        return jwtService.generateToken(email);
    }

    // Endpoint pra validar token
    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        boolean isValid = jwtService.validateToken(token);
        if (isValid) {
            String email = jwtService.extractEmail(token);
            return "Token válido! Email: " + email;
        } else {
            return "Token inválido ou expirado!";
        }
    }
}