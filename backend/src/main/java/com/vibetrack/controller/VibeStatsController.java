package com.vibetrack.controller;

import com.vibetrack.service.VibeStatsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stats")
public class VibeStatsController {

    private final VibeStatsService vibeStatsService;

    public VibeStatsController(VibeStatsService vibeStatsService) {
        this.vibeStatsService = vibeStatsService;
    }

    // ====== EMOÇÕES ======
    @GetMapping("/emotion")
    public Map<String, Long> emotionStats(@RequestParam Long userId) {
        return vibeStatsService.countByEmotion(userId);
    }

    // ====== GÊNEROS ======
    @GetMapping("/top-genres")
    public Map<String, Long> topGenres(@RequestParam Long userId) {
        return vibeStatsService.topGenres(userId);
    }

    // ====== ARTISTAS ======
    @GetMapping("/top-artists")
    public Map<String, Long> topArtists(@RequestParam Long userId) {
        return vibeStatsService.topArtists(userId);
    }

    // ====== TIMELINE ======
    @GetMapping("/timeline")
    public List<Map<String, Object>> timeline(@RequestParam Long userId) {
        return vibeStatsService.timeline(userId);
    }
}
