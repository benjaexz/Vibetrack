package com.vibetrack.controller;

import com.vibetrack.dto.EmotionStatsDTO;
import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/emotions/{userId}")
    public ResponseEntity<List<EmotionStatsDTO>> getEmotionStats(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.countByEmotion(userId));
    }

    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<TimelineEntryDTO>> getTimeline(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.timeline(userId));
    }

    @GetMapping("/top-artists/{userId}")
    public ResponseEntity<List<TopItemDTO>> getTopArtists(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.topArtists(userId));
    }

    @GetMapping("/top-genres/{userId}")
    public ResponseEntity<List<TopItemDTO>> getTopGenres(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.topGenres(userId));
    }
}
