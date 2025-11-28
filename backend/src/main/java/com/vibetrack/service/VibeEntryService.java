package com.vibetrack.service;

import com.vibetrack.dto.VibeEntryRequest;
import com.vibetrack.dto.VibeEntryResponse;
import com.vibetrack.model.Emotion;
import com.vibetrack.model.VibeEntry;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class VibeEntryService {

    private final VibeEntryRepository vibeEntryRepository;

    public VibeEntryService(VibeEntryRepository vibeEntryRepository) {
        this.vibeEntryRepository = vibeEntryRepository;
    }

    public VibeEntryResponse create(VibeEntryRequest request) {

        VibeEntry entry = new VibeEntry();
        entry.setUserId(request.userId);
        entry.setMusica(request.musica);
        entry.setArtista(request.artista);
        entry.setGenero(request.genero);
        entry.setEmocao(request.emocao);
        entry.setTimestamp(
                request.timestamp != null ? Instant.parse(request.timestamp) : Instant.now()
        );

        VibeEntry saved = vibeEntryRepository.save(entry);

        return new VibeEntryResponse(
                saved.getId(),
                saved.getMusica(),
                saved.getArtista(),
                saved.getGenero(),
                saved.getEmocao(),
                saved.getTimestamp(),
                saved.getUserId()
        );
    }

    public List<VibeEntryResponse> findAllByUser(Long userId) {
        return vibeEntryRepository.findByUserId(userId)
                .stream()
                .map(entry -> new VibeEntryResponse(
                        entry.getId(),
                        entry.getMusica(),
                        entry.getArtista(),
                        entry.getGenero(),
                        entry.getEmocao(),
                        entry.getTimestamp(),
                        entry.getUserId()
                ))
                .toList();
    }

    public void delete(Long id, Long userId) {
        VibeEntry entry = vibeEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        if (!entry.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized deletion");
        }

        vibeEntryRepository.delete(entry);
    }
}
