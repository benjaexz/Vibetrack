package com.vibetrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "vibe_entries")
public class VibeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private AppUser user;

    @NotBlank
    private String musica;

    @NotBlank
    private String artista;

    @NotBlank
    private String genero;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Emotion emocao;

    @NotNull
    private Instant timestamp;

    public VibeEntry() {}

    public VibeEntry(AppUser user, String musica, String artista, String genero, Emotion emocao, Instant timestamp) {
        this.user = user;
        this.musica = musica;
        this.artista = artista;
        this.genero = genero;
        this.emocao = emocao;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Emotion getEmocao() {
        return emocao;
    }

    public void setEmocao(Emotion emocao) {
        this.emocao = emocao;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
