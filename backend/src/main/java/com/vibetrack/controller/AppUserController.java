package com.vibetrack.controller;

import com.vibetrack.model.AppUser;
import com.vibetrack.repository.AppUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // permite que o Angular acesse
@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserRepository repository;

    public AppUserController(AppUserRepository repository) {
        this.repository = repository;
    }

    // Criar usuário (POST)
    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return repository.save(user);
    }

    // Listar todos os usuários (GET)
    @GetMapping
    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    // Atualizar usuário pelo id (PUT)
    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable Long id, @RequestBody AppUser userDetails) {
        AppUser user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id " + id));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());

        return repository.save(user);
    }

    // Deletar usuário pelo id (DELETE)
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return "Usuário com id " + id + " deletado com sucesso!";
    }

}
