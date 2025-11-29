package com.vibetrack.service;

import com.vibetrack.dto.UserStatsDTO;
import com.vibetrack.entity.AppUser;
import com.vibetrack.entity.VibeEntry;
import com.vibetrack.repository.UserRepository;
import com.vibetrack.repository.VibeEntryRepository;
import com.vibetrack.repository.VibeStatsRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VibeEntryService {

    private final VibeEntryRepository vibeEntryRepository;
    private final VibeStatsRepository vibeStatsRepository;
    private final UserRepository userRepository;

    public VibeEntryService(
            VibeEntryRepository vibeEntryRepository,
            VibeStatsRepository vibeStatsRepository,
            UserRepository userRepository
    ) {
        this.vibeEntryRepository = vibeEntryRepository;
        this.vibeStatsRepository = vibeStatsRepository;
        this.userRepository = userRepository;
    }

    // --------------------------------------------
    // CRUD Básico
    // --------------------------------------------
    public VibeEntry save(VibeEntry vibeEntry) {
        return vibeEntryRepository.save(vibeEntry);
    }

    public List<VibeEntry> getAllByUserId(Long userId) {
        return vibeEntryRepository.findByUserId(userId);
    }

    public List<VibeEntry> getAllByUser(AppUser user) {
        return vibeEntryRepository.findByUserOrderByTimestampDesc(user);
    }

    public List<VibeEntry> getByUserAndTimeRange(AppUser user, Instant start, Instant end) {
        return vibeEntryRepository.findByUserAndTimestampBetween(user, start, end);
    }

    // --------------------------------------------
    // ESTATÍSTICAS
    // --------------------------------------------
    public UserStatsDTO getUserStats(Long userId) {

        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 1) Emoções
        List<Object[]> emotionRaw = vibeStatsRepository.countByEmotion(userId);

        Map<String, Long> emotionCount = emotionRaw.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> (Long) row[1]
                ));

        // 2) Gêneros
        List<Object[]> generoRaw = vibeStatsRepository.topGeneros(userId);

        Map<String, Long> generos = generoRaw.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> (Long) row[1]
                ));

        // 3) Artistas
        List<Object[]> artistasRaw = vibeStatsRepository.topArtistas(userId);

        Map<String, Long> artistas = artistasRaw.stream()
