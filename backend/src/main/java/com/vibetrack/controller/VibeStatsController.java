package com.vibetrack.controller;

import com.vibetrack.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/stats")
public class VibeStatsController {

    private final StatisticsService statisticsService;

    public VibeStatsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/emotion")
    public Map<String, Long> emotionStats(@RequestParam Long userId) {
        return statisticsService.countByEmotion(userId);
    }

    @GetMapping("/top-genres")
    public Map<String, Long> topGenres(@RequestParam Long userId) {
        return statisticsService.topGenres(userId);
    }

    @GetMapping("/top-artists")
    public Map<String, Long> topArtists(@RequestParam Long userId) {
        return statisticsService.topArtists(userId);
    }

    @GetMapping("/timeline")
    public Map<String, Long> timeline(@RequestParam Long userId) {
        return statisticsService.timeline(userId);
    }
}
