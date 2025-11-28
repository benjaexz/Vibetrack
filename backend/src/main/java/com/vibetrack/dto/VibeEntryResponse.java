package com.vibetrack.dto;

import com.vibetrack.model.Emotion;

import java.time.Instant;

public class VibeEntryResponse {

    private Long id;
    private String musica;
    private String artista;
    private String genero;
    private Emotion emocao;
    private Instant timestamp;

    public VibeEntryResponse() {}

    public VibeEntryResponse(Long id, String musica, String artista, String genero, Emotion emocao, Instant timestamp) {
        this.id = id;
        this.musica = musica;
        this.artista = artista;
        this.genero = genero;
        this.emocao = emocao;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }

    public String getMusica() { return musica; }

    public String getArtista() { return artista; }

    public String getGenero() { return genero; }

    public Emotion getEmocao() { return emocao; }

    public Instant getTimestamp() { return timestamp; }
}
