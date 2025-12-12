package com.vibetrack.service;

import com.vibetrack.entity.User;
import com.vibetrack.repository.VibeStatsRepository;
import com.vibetrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Map<String, Long> topGenres(Long userId) {
        return vibeStatsRepository.topGenres(userId).stream()
                .collect(Collectors.toMap(
                        row -> (String) ((Object[]) row)[0],
                        row -> (Long) ((Object[]) row)[1]
                ));
    }

    public Map<String, Long> countByEmotion(Long userId) {
        return vibeStatsRepository.countByEmotion(userId).stream()
                .collect(Collectors.toMap(
                        row -> (String) ((Object[]) row)[0],
                        row -> (Long) ((Object[]) row)[1]
                ));
    }

    public Map<String, Long> topArtists(Long userId) {
        return vibeStatsRepository.topArtists(userId).stream()
                .collect(Collectors.toMap(
                        row -> (String) ((Object[]) row)[0],
                        row -> (Long) ((Object[]) row)[1]
                ));
    }

    public Map<String, Long> timeline(Long userId) {
        return vibeStatsRepository.timeline(userId).stream()
                .collect(Collectors.toMap(
                        row -> ((Date) ((Object[]) row)[0]).toString(),
                        row -> (Long) ((Object[]) row)[1]
                ));
    }
}
