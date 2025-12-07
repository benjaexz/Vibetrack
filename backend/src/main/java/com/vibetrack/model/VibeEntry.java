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
    private String songTitle;

    @NotBlank
    private String artist;

    @NotBlank
    private String genre;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Emotion emotion;

    @NotNull
    private Instant timestamp;

    public VibeEntry() {}

    public VibeEntry(AppUser user, String songTitle, String artist, String genre, Emotion emotion, Instant timestamp) {
        this.user = user;
        this.songTitle = songTitle;
        this.artist = artist;
        this.genre = genre;
        this.emotion = emotion;
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

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
