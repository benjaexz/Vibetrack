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
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final VibeEntryRepository vibeEntryRepository;
    private final UserRepository userRepository;

    public StatisticsService(VibeEntryRepository vibeEntryRepository, UserRepository userRepository) {
        this.vibeEntryRepository = vibeEntryRepository;
        this.userRepository = userRepository;
    }

    public List<EmotionStatsDTO> countByEmotion(Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<VibeEntry> list = vibeEntryRepository.findByUserId(userId);

        Map<Emotion, Long> grouped = list.stream()
                .collect(Collectors.groupingBy(VibeEntry::getEmocao, Collectors.counting()));

        return grouped.entrySet().stream()
                .map(e -> new EmotionStatsDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<TimelineEntryDTO> timeline(Long userId) {
        List<VibeEntry> list = vibeEntryRepository.findByUserId(userId);

        Map<LocalDate, Long> grouped =
                list.stream()
                        .collect(Collectors.groupingBy(
                                v -> v.getTimestamp().atZone(ZoneId.systemDefault()).toLocalDate(),
                                Collectors.counting()
                        ));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new TimelineEntryDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<TopItemDTO> topArtists(Long userId) {
        List<VibeEntry> list = vibeEntryRepository.findByUserId(userId);

        Map<String, Long> grouped =
                list.stream()
                        .collect(Collectors.groupingBy(VibeEntry::getArtista, Collectors.counting()));

        return grouped.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(e -> new TopItemDTO(e.getKey(), e.getValue()))
                .toList();
    }

    public List<TopItemDTO> topGenres(Long userId) {
        List<VibeEntry> list = vibeEntryRepository.findByUserId(userId);

        Map<String, Long> grouped =
                list.stream()
                        .collect(Collectors.groupingBy(VibeEntry::getGenero, Collectors.counting()));

        return grouped.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(e -> new TopItemDTO(e.getKey(), e.getValue()))
                .toList();
    }
}
