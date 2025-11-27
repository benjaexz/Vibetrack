package com.vibetrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "username é obrigatório") @Size(min = 2, max = 50) String username,
        @Email(message = "email inválido") @NotBlank(message = "email é obrigatório") String email,
        @NotBlank(message = "senha é obrigatória") @Size(min = 6, message = "senha precisa ter ao menos 6 caracteres") String password
) {}
