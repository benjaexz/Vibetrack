package com.vibetrack.controller;

import com.vibetrack.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/emotion/{userId}")
    public ResponseEntity<Map<String, Long>> countByEmotion(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.countByEmotion(userId));
    }

    @GetMapping("/genre/{userId}")
    public ResponseEntity<Map<String, Long>> topGeneros(@PathVariable Long userId) {
        return ResponseEntity.ok(statisticsService.topGenres(userId));
    }
}
