package com.vibetrack.service;

import com.vibetrack.dto.VibeEntryRequest;
import com.vibetrack.dto.VibeEntryResponse;
import com.vibetrack.model.AppUser;
import com.vibetrack.model.VibeEntry;
import com.vibetrack.repository.UserRepository;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VibeEntryService {

    private final VibeEntryRepository vibeEntryRepository;
    private final UserRepository userRepository;

    public VibeEntryService(VibeEntryRepository vibeEntryRepository, UserRepository userRepository) {
        this.vibeEntryRepository = vibeEntryRepository;
        this.userRepository = userRepository;
    }

    public VibeEntryResponse create(VibeEntryRequest request) {

        AppUser user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        VibeEntry entry = new VibeEntry();
        entry.setMusica(request.musica());
        entry.setArtista(request.artista());
        entry.setGenero(request.genero());
        entry.setEmocao(request.emocao());
        entry.setTimestamp(request.timestamp() != null ?
                Instant.parse(request.timestamp()) :
                Instant.now());
        entry.setUser(user);

        VibeEntry saved = vibeEntryRepository.save(entry);

        return toResponse(saved);
    }

    public List<VibeEntryResponse> findAllByUser(Long userId) {
        List<VibeEntry> list = vibeEntryRepository.findByUserId(userId);
        return list.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public void delete(Long id, Long userId) {
        VibeEntry entry = vibeEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vibe não encontrada"));

        if (!entry.getUser().getId().equals(userId)) {
            throw new RuntimeException("A vibe não pertence ao usuário");
        }

        vibeEntryRepository.delete(entry);
    }

    private VibeEntryResponse toResponse(VibeEntry entry) {
        return new VibeEntryResponse(
                entry.getId(),
                entry.getMusica(),
                entry.getArtista(),
                entry.getGenero(),
                entry.getEmocao(),
                entry.getTimestamp(),
                entry.getUser().getId()
        );
    }
}
