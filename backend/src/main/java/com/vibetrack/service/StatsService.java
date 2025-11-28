package com.vibetrack.service;

import com.vibetrack.dto.EmotionStatsDTO;
import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.model.Emotion;
import com.vibetrack.repository.VibeStatsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatsService {

    private final VibeStatsRepository repo;

    public StatsService(VibeStatsRepository repo) {
        this.repo = repo;
    }

    public List<EmotionStatsDTO> getEmotionStats(Long userId) {
        return repo.countByEmotion(userId).stream()
                .map(row -> new EmotionStatsDTO(
                        (Emotion) row[0],
                        (Long) row[1]
                ))
                .toList();
    }

    public List<TopItemDTO> getTopGeneros(Long userId) {
        return repo.topGeneros(userId).stream()
                .map(row -> new TopItemDTO(
                        (String) row[0],
                        (Long) row[1]
                ))
                .toList();
    }

    public List<TopItemDTO> getTopArtistas(Long userId) {
        return repo.topArtistas(userId).stream()
                .map(row -> new TopItemDTO(
                        (String) row[0],
                        (Long) row[1]
                ))
                .toList();
    }

    public List<TimelineEntryDTO> getTimeline(Long userId) {
        return repo.timeline(userId).stream()
                .map(row -> new TimelineEntryDTO(
                        (LocalDate) row[0],
                        (Long) row[1]
                ))
                .toList();
    }
}
