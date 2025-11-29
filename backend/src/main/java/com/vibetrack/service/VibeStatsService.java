package com.vibetrack.service;

import com.vibetrack.dto.stats.EmotionCountResponse;
import com.vibetrack.dto.stats.TopArtistResponse;
import com.vibetrack.dto.stats.TopGenreResponse;
import com.vibetrack.dto.stats.TimelinePointResponse;
import com.vibetrack.repository.VibeStatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VibeStatsService {

    private final VibeStatsRepository vibeStatsRepository;

    public VibeStatsService(VibeStatsRepository vibeStatsRepository) {
        this.vibeStatsRepository = vibeStatsRepository;
    }

    // Estatísticas por emoção
    public List<EmotionCountResponse> countByEmotion(Long userId) {
        return vibeStatsRepository.countByEmotion(userId)
                .stream()
                .map(row -> new EmotionCountResponse(
                        (com.vibetrack.model.Emotion) row[0],
                        ((Number) row[1]).longValue()
                )).toList();
    }

    // Top gêneros
    public List<TopGenreResponse> topGeneros(Long userId) {
        return vibeStatsRepository.topGeneros(userId)
                .stream()
                .map(row -> new TopGenreResponse(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                )).toList();
    }

    // Top artistas
    public List<TopArtistResponse> topArtistas(Long userId) {
        return vibeStatsRepository.topArtistas(userId)
                .stream()
                .map(row -> new TopArtistResponse(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                )).toList();
    }

    // Timeline diária
    public List<TimelinePointResponse> timeline(Long userId) {
        return vibeStatsRepository.timeline(userId)
                .stream()
                .map(row -> new TimelinePointResponse(
                        row[0].toString(),
                        ((Number) row[1]).longValue()
                )).toList();
    }
}
