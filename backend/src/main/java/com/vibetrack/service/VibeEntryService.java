package com.vibetrack.service;

import com.vibetrack.dto.VibeEntryRequest;
import com.vibetrack.dto.VibeEntryResponse;
import com.vibetrack.model.AppUser;
import com.vibetrack.model.VibeEntry;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class VibeEntryService {

    private final VibeEntryRepository repository;

    public VibeEntryService(VibeEntryRepository repository) {
        this.repository = repository;
    }

    public VibeEntryResponse create(VibeEntryRequest request, AppUser user) {
        VibeEntry entry = new VibeEntry();

        entry.setUser(user);
        entry.setMusica(request.musica());
        entry.setArtista(request.artista());
        entry.setGenero(request.genero());
        entry.setEmocao(request.emocao());
        entry.setTimestamp(
                request.timestamp() != null
                        ? Instant.parse(request.timestamp())
                        : Instant.now()
        );

        repository.save(entry);

        return new VibeEntryResponse(
                entry.getId(),
                entry.getMusica(),
                entry.getArtista(),
                entry.getGenero(),
                entry.getEmocao(),
                entry.getTimestamp(),
                user.getId()
        );
    }

    public List<VibeEntryResponse> findAllByUser(AppUser user) {
        return repository.findByUserOrderByTimestampDesc(user)
                .stream()
                .map(v -> new VibeEntryResponse(
                        v.getId(),
                        v.getMusica(),
                        v.getArtista(),
                        v.getGenero(),
                        v.getEmocao(),
                        v.getTimestamp(),
                        user.getId()
                ))
                .toList();
    }

    public void delete(Long id, AppUser user) {
        VibeEntry entry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vibe não encontrada."));

        if (!entry.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Não autorizado.");
        }

        repository.delete(entry);
    }
}
