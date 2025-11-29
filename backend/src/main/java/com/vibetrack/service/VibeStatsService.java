package com.vibetrack.service;

import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.model.Emotion;
import com.vibetrack.repository.VibeStatsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VibeStatsService {

    private final VibeStatsRepository statsRepository;

    public VibeStatsService(VibeStatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    // Emoções mais registradas
    public List<TopItemDTO> countByEmotion(Long userId) {
        return statsRepository.countByEmotion(userId)
                .stream()
                .map(obj -> new TopItemDTO(
                        ((Emotion) obj[0]).name(),
                        (Long) obj[1]
                ))
                .collect(Collectors.toList());
    }

    // Gêneros mais comuns
    public List<TopItemDTO> topGeneros(Long userId) {
        return statsRepository.topGeneros(userId)
                .stream()
                .map(obj -> new TopItemDTO(
                        (String) obj[0],
                        (Long) obj[1]
                ))
                .collect(Collectors.toList());
    }

    // Artistas mais comuns
    public List<TopItemDTO> topArtistas(Long userId) {
        return statsRepository.topArtistas(userId)
                .stream()
                .map(obj -> new TopItemDTO(
                        (String) obj[0],
                        (Long) obj[1]
                ))
                .collect(Collectors.toList());
    }

    // Timeline (registros por dia)
    public List<TimelineEntryDTO> timeline(Long userId) {
        return statsRepository.timeline(userId)
                .stream()
                .map(obj -> new TimelineEntryDTO(
                        (LocalDate) obj[0],
                        (Long) obj[1]
                ))
                .collect(Collectors.toList());
    }
}
