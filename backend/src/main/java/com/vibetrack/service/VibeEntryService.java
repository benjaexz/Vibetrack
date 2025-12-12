package com.vibetrack.service;

import com.vibetrack.dto.VibeEntryRequest;
import com.vibetrack.dto.VibeEntryResponse;
import com.vibetrack.entity.User;
import com.vibetrack.entity.VibeEntry;
import com.vibetrack.model.Emotion;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class VibeEntryService {

    private final VibeEntryRepository vibeEntryRepository;

    public VibeEntryService(VibeEntryRepository vibeEntryRepository) {
        this.vibeEntryRepository = vibeEntryRepository;
    }

    public VibeEntry save(VibeEntry vibeEntry) {
        return vibeEntryRepository.save(vibeEntry);
    }

    public List<VibeEntry> getAllByUserId(Long userId) {
        return vibeEntryRepository.findByUserId(userId);
    }

    public List<VibeEntry> getAllByUser(User user) {
        return vibeEntryRepository.findByUserOrderByTimestampDesc(user);
    }

    public List<VibeEntry> getByUserAndTimeRange(User user, Instant start, Instant end) {
        return vibeEntryRepository.findByUserAndTimestampBetween(user, start, end);
    }

    public VibeEntryResponse create(VibeEntryRequest request, User user) {
        VibeEntry entry = new VibeEntry();
        entry.setUser(user);
        entry.setMusic(request.getMusic());
        entry.setArtist(request.getArtist());
        entry.setGenre(request.getGenre());
        entry.setEmotion(request.getEmotion().name());

        Instant ts = Instant.now();
        if (request.getTimestamp() != null) {
            try {
                ts = Instant.parse(request.getTimestamp());
            } catch (DateTimeParseException ignored) {
                // se vier inválido, mantém agora
            }
        }
        entry.setTimestamp(ts);

        VibeEntry saved = vibeEntryRepository.save(entry);
        return toResponse(saved);
    }

    public List<VibeEntryResponse> findAllByUser(User user) {
        return getAllByUser(user).stream()
                .map(this::toResponse)
                .toList();
    }

    public void delete(Long id, User user) {
        // se quiser, pode validar se o id pertence ao user antes
        vibeEntryRepository.deleteById(id);
    }

    private VibeEntryResponse toResponse(VibeEntry entry) {
        VibeEntryResponse resp = new VibeEntryResponse();
        resp.setId(entry.getId());
        resp.setMusic(entry.getMusic());
        resp.setArtist(entry.getArtist());
        resp.setGenre(entry.getGenre());

        Emotion emotion = null;
        try {
            if (entry.getEmotion() != null) {
                emotion = Emotion.valueOf(entry.getEmotion());
            }
        } catch (IllegalArgumentException ignored) {
        }
        resp.setEmotion(emotion);

        resp.setTimestamp(entry.getTimestamp());
        return resp;
    }
}
