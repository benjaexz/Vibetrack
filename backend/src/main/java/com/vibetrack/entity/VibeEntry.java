package com.vibetrack.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "vibe_entries")
public class VibeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String music;
    private String artist;
    private String emotion;
    private String genre;

    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public VibeEntry() {
        this.timestamp = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
