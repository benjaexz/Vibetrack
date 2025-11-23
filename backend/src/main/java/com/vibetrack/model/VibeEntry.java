package com.vibetrack.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "vibe_entries")
public class VibeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    private String musica;
    private String artista;
    private String genero;

    @Enumerated(EnumType.STRING)
    private Emotion emocao;

    private Instant timestamp;

    // CONSTRUTOR VAZIO (necessário para o JPA)
    public VibeEntry() {}

    // CONSTRUTOR CORRETO QUE O SERVICE ESTÁ USANDO
    public VibeEntry(AppUser user, String musica, String artista, String genero, Emotion emocao, Instant timestamp) {
        this.user = user;
        this.musica = musica;
        this.artista = artista;
        this.genero = genero;
        this.emocao = emocao;
        this.timestamp = timestamp;
    }

    // GETTERS E SETTERS
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
