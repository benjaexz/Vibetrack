package com.vibetrack.service;

import com.vibetrack.dto.EmotionStatsDTO;
import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.model.AppUser;
import com.vibetrack.model.Emotion;
import com.vibetrack.model.VibeEntry;
import com.vibetrack.repository.UserRepository;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final VibeEntryRepository vibeRepo;
    private final UserRepository userRepo;

    public StatsService(VibeEntryRepository vibeRepo, UserRepository userRepo) {
        this.vibeRepo = vibeRepo;
        this.userRepo = userRepo;
    }

    private AppUser getUser(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Timeline diária — contagem de entradas por dia
    public List<TimelineEntryDTO> getTimeline(Long userId) {
        AppUser user = getUser(userId);

        List<VibeEntry> vibes = vibeRepo.findByUserOrderByTimestampDesc(user);

        Map<LocalDate, Long> counts = vibes.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getTimestamp().atZone(ZoneOffset.UTC).toLocalDate(),
                        Collectors.counting()
                ));

        return counts.entrySet().stream()
                .map(e -> new TimelineEntryDTO(e.getKey(), e.getValue()))
                .sorted(Comparator.comparing(TimelineEntryDTO::getDate))
                .toList();
    }

    // Top artistas ou gêneros
    public List<TopItemDTO> getTop(Long userId, String field) {
        AppUser user = getUser(userId);

        List<VibeEntry> vibes = vibeRepo.findByUserOrderByTimestampDesc(user);

        Map<String, Long> counts = new HashMap<>();

        for (VibeEntry v : vibes) {
            String key = field.equals("artista") ? v.getArtista() : v.getGenero();
            if (key != null) counts.merge(key, 1L, Long::sum);
        }

        return counts.entrySet().stream()
                .map(e -> new TopItemDTO(e.getKey(), e.getValue()))
                .sorted((a, b) -> Long.compare(b.getCount(), a.getCount()))
                .limit(10)
                .toList();
    }

    // Estatísticas de emoções
    public List<EmotionStatsDTO> getEmotionStats(Long userId) {
        AppUser user = getUser(userId);

        List<VibeEntry> vibes = vibeRepo.findByUserOrderByTimestampDesc(user);

        Map<Emotion, Long> counts = Arrays.stream(Emotion.values())
                .collect(Collectors.toMap(e -> e, e -> 0L));

        for (VibeEntry v : vibes) {
            counts.merge(v.getEmocao(), 1L, Long::sum);
        }

        return counts.entrySet().stream()
                .map(e -> new EmotionStatsDTO(e.getKey(), e.getValue()))
                .sorted((a, b) -> Long.compare(b.getCount(), a.getCount()))
                .toList();
    }
}
