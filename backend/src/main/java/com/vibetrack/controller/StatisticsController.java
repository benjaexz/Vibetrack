package com.vibetrack.controller;

import com.vibetrack.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/emocao/{userId}")
    public ResponseEntity<Map<String, Long>> emocao(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.countByEmotion(userId));
    }

    @GetMapping("/artistas/{userId}")
    public ResponseEntity<Map<String, Long>> artistas(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.topArtistas(userId));
    }

    @GetMapping("/generos/{userId}")
    public ResponseEntity<Map<String, Long>> generos(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.topGeneros(userId));
    }

    @GetMapping("/timeline/{userId}")
    public ResponseEntity<Map<String, Long>> timeline(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.timeline(userId));
    }
}
