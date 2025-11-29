package com.vibetrack.service;

import com.vibetrack.entity.AppUser;
import com.vibetrack.repository.VibeStatsRepository;
import com.vibetrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final VibeStatsRepository vibeStatsRepository;
    private final UserRepository userRepository;

    public StatisticsService(VibeStatsRepository vibeStatsRepository, UserRepository userRepository) {
        this.vibeStatsRepository = vibeStatsRepository;
        this.userRepository = userRepository;
    }

    private AppUser getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // EMOÇÕES
    public Map<String, Long> countByEmotion(Long userId) {
        return vibeStatsRepository.countByEmotion(userId).stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    // ARTISTAS
    public Map<String, Long> topArtistas(Long userId) {
        return vibeStatsRepository.topArtistas(userId).stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    // GÊNEROS
    public Map<String, Long> topGeneros(Long userId) {
        return vibeStatsRepository.topGeneros(userId).stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]
                ));
    }

    // TIMELINE
    public Map<String, Long> timeline(Long userId) {
        return vibeStatsRepository.timeline(userId).stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),  // data
                        row -> (Long) row[1]
                ));
    }
}
