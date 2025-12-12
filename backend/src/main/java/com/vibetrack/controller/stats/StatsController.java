package com.vibetrack.controller.stats;

import com.vibetrack.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {

    private final StatisticsService statisticsService;

    public StatsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{userId}/emotions")
    public ResponseEntity<Map<String, Long>> emotionStats(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.countByEmotion(userId));
    }

    @GetMapping("/{userId}/genres")
    public ResponseEntity<Map<String, Long>> genreStats(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.topGenres(userId));
    }

    @GetMapping("/{userId}/artists")
    public ResponseEntity<Map<String, Long>> artistStats(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.topArtists(userId));
    }

    @GetMapping("/{userId}/timeline")
    public ResponseEntity<Map<String, Long>> timeline(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.timeline(userId));
    }
}
