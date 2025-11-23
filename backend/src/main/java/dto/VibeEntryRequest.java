package com.vibetrack.controller.dto;

import com.vibetrack.model.Emotion;

public class VibeEntryRequest {
    public Long userId;
    public String musica;
    public String artista;
    public String genero;
    public Emotion emocao;
    public String timestamp; // ISO-8601 (opcional) - se nulo, o backend usa Instant.now()
}
