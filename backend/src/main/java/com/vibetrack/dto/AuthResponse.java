package com.vibetrack.dto;

public record AuthResponse(
        String token,
        Long userId,
        String username,
        String email
) {}
