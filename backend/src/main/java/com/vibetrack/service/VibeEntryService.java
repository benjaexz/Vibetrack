package com.vibetrack.service;

import com.vibetrack.model.VibeEntry;
import com.vibetrack.model.Emotion;
import com.vibetrack.model.AppUser;

import com.vibetrack.repository.VibeEntryRepository;
import com.vibetrack.repository.AppUserRepository;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Service
public class VibeEntryService {

    private final VibeEntryRepository vibeRepo;
    private final AppUserRepository userRepo;

    public VibeEntryService(VibeEntryRepository vibeRepo, AppUserRepository userRepo) {
        this.vibeRepo = vibeRepo;
        this.userRepo = userRepo;
    }

    public VibeEntry createVibe(Long userId, String musica, String artista, String genero, Emotion emocao, Instant timestamp) {
        AppUser user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + userId));

        VibeEntry v = new VibeEntry(
                user,
                musica,
                artista,
                genero,
                emocao,
                timestamp == null ? Instant.now() : timestamp
        );

        return vibeRepo.save(v);
    }

    public List<VibeEntry> listByUser(Long userId) {
        return vibeRepo.findByUserId(userId);
    }

    public List<VibeEntry> listAll() {
        return vibeRepo.findAll();
    }

    public Optional<VibeEntry> findById(Long id) {
        return vibeRepo.findById(id);
    }

    public void delete(Long id) {
        vibeRepo.deleteById(id);
    }

    public VibeEntry updateVibe(
            Long id,
            Long userId,
            String musica,
            String artista,
            String genero,
            Emotion emocao,
            Instant timestamp
    ) {

        VibeEntry existing = vibeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("VibeEntry não encontrada: " + id));

        if (userId != null && !userId.equals(existing.getUser().getId())) {
            AppUser user = userRepo.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + userId));
            existing.setUser(user);
        }

        if (musica != null) existing.setMusica(musica);
        if (artista != null) existing.setArtista(artista);
        if (genero != null) existing.setGenero(genero);
        if (emocao != null) existing.setEmocao(emocao);
        if (timestamp != null) existing.setTimestamp(timestamp);

        return vibeRepo.save(existing);
    }
}