package com.vibetrack.service;

import com.vibetrack.entity.VibeEntry;
import com.vibetrack.repository.VibeEntryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VibeStatsService {

    private final VibeEntryRepository vibeEntryRepository;

    public VibeStatsService(VibeEntryRepository vibeEntryRepository) {
        this.vibeEntryRepository = vibeEntryRepository;
    }

    public Map<String, Long> countEmotions(Long userId) {
        List<VibeEntry> entries = vibeEntryRepository.findByUserId(userId);
        Map<String, Long> emotionCount = new HashMap<>();

        for (VibeEntry entry : entries) {
            String emotionName = entry.getEmotion();
            if (emotionName == null) continue;
            emotionCount.merge(emotionName, 1L, Long::sum);
        }

        return emotionCount;
    }
}
