package com.vibetrack.service;

import com.vibetrack.entity.User;
import com.vibetrack.entity.VibeEntry;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

    public List<VibeEntry> getAllByUser(User user) {
        return vibeEntryRepository.findByUserOrderByTimestampDesc(user);
    }

    public List<VibeEntry> getByUserAndTimeRange(User user, Instant start, Instant end) {
        return vibeEntryRepository.findByUserAndTimestampBetween(user, start, end);
    }
}
