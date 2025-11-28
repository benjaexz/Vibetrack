package com.vibetrack.dto;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {}
