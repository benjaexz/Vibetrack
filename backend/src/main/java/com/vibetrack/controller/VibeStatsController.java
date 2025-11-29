package com.vibetrack.controller;

import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.service.VibeStatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vibes/stats")
public class VibeStatsController {

    private final VibeStatsService statsService;

    public VibeStatsController(VibeStatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/emotions/{userId}")
    public List<TopItemDTO> countByEmotion(@PathVariable Long userId) {
        return statsService.countByEmotion(userId);
    }

    @GetMapping("/genres/{userId}")
    public List<TopItemDTO> topGeneros(@PathVariable Long userId) {
        return statsService.topGeneros(userId);
    }

    @GetMapping("/artists/{userId}")
    public List<TopItemDTO> topArtistas(@PathVariable Long userId) {
        return statsService.topArtistas(userId);
    }

    @GetMapping("/timeline/{userId}")
    public List<TimelineEntryDTO> timeline(@PathVariable Long userId) {
        return statsService.timeline(userId);
    }
}
