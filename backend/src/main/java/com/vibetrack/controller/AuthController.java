package com.vibetrack.controller;

import com.vibetrack.entity.User;
import com.vibetrack.service.UserService;
import com.vibetrack.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");

        if (userService.getByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Username já existe");
        }
        if (userService.getByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Email já existe");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userService.save(user);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Optional<User> userOpt = userService.getByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        User user = userOpt.get();
        // Aqui você validaria a senha com o PasswordEncoder
        // Por enquanto, vamos retornar sucesso

        String token = jwtUtil.generateToken(username);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login bem-sucedido");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
