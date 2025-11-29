package com.vibetrack.service;

import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.UserStatsDTO;
import com.vibetrack.entity.AppUser;
import com.vibetrack.entity.VibeEntry;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<VibeEntry> getAllByUser(AppUser user) {
        return vibeEntryRepository.findByUserOrderByTimestampDesc(user);
    }

    public List<VibeEntry> getByUserAndTimeRange(AppUser user, Instant start, Instant end) {
        return vibeEntryRepository.findByUserAndTimestampBetween(user, start, end);
    }

    // -------------------------------------------------------------
    // 📊 MÉTODO DE ESTATÍSTICAS COMPLETO
    // -------------------------------------------------------------
    public UserStatsDTO getUserStats(Long userId) {

        List<VibeEntry> entries = vibeEntryRepository.findByUserId(userId);

        // top genres
        Map<String, Long> topGenres = entries.stream()
                .map(VibeEntry::getGenero)
                .filter(g -> g != null && !g.isBlank())
                .collect(Collectors.groupingBy(g -> g, Collectors.counting()));

        // top artists
        Map<String, Long> topArtists = entries.stream()
                .map(VibeEntry::getArtista)
                .filter(a -> a != null && !a.isBlank())
                .collect(Colle
