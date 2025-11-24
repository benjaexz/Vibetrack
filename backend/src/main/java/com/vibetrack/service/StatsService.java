package com.vibetrack.service;

import com.vibetrack.dto.EmotionStatsDTO;
import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.model.Emotion;
import com.vibetrack.model.VibeEntry;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService {

    @Autowired
    private VibeEntryRepository vibeEntryRepository;

    // Retorna estatísticas de emoções
    public List<EmotionStatsDTO> getEmotionStats() {
        List<Object[]> results = vibeEntryRepository.countByEmotion();
        List<EmotionStatsDTO> stats = new ArrayList<>();

        for (Object[] result : results) {
            Emotion emotion = (Emotion) result[0];
            Long count = (Long) result[1];
            stats.add(new EmotionStatsDTO(emotion, count));
        }

        return stats;
    }

    // Retorna top N gêneros
    public List<TopItemDTO> getTopGenres(int limit) {
        List<Object[]> results = vibeEntryRepository.findTopGenres();
        List<TopItemDTO> topGenres = new ArrayList<>();

        int count = 0;
        for (Object[] result : results) {
            if (count >= limit) break;
            String name = (String) result[0];
            Long total = (Long) result[1];
            topGenres.add(new TopItemDTO(name, total));
            count++;
        }

        return topGenres;
    }

    // Retorna top N artistas
    public List<TopItemDTO> getTopArtists(int limit) {
        List<Object[]> results = vibeEntryRepository.findTopArtists();
        List<TopItemDTO> topArtists = new ArrayList<>();

        int count = 0;
        for (Object[] result : results) {
            if (count >= limit) break;
            String name = (String) result[0];
            Long total = (Long) result[1];
            topArtists.add(new TopItemDTO(name, total));
            count++;
        }

        return topArtists;
    }

    // Retorna timeline dos últimos N dias
    public List<TimelineEntryDTO> getTimeline(int days) {
        Instant startDate = Instant.now().minus(days, ChronoUnit.DAYS);
        List<VibeEntry> vibes = vibeEntryRepository.findVibesAfterDate(startDate);

        // Agrupar por data
        Map<LocalDate, Long> dateCountMap = new HashMap<>();

        for (VibeEntry vibe : vibes) {
            LocalDate date = vibe.getTimestamp()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            dateCountMap.put(date, dateCountMap.getOrDefault(date, 0L) + 1);
        }

        // Converter para lista de DTOs
        List<TimelineEntryDTO> timeline = new ArrayList<>();
        for (Map.Entry<LocalDate, Long> entry : dateCountMap.entrySet()) {
            timeline.add(new TimelineEntryDTO(entry.getKey(), entry.getValue()));
        }

        // Ordenar por data
        timeline.sort((a, b) -> a.getDate().compareTo(b.getDate()));

        return timeline;
    }
}