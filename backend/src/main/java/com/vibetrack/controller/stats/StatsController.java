package com.vibetrack.controller.stats;

import com.vibetrack.dto.stats.EmotionCountResponse;
import com.vibetrack.dto.stats.TopArtistResponse;
import com.vibetrack.dto.stats.TopGenreResponse;
import com.vibetrack.dto.stats.TimelinePointResponse;
import com.vibetrack.service.VibeStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {

    private final VibeStatsService vibeStatsService;

    public StatsController(VibeStatsService vibeStatsService) {
        this.vibeStatsService = vibeStatsService;
    }

    @GetMapping("/{userId}/emotions")
    public ResponseEntity<List<EmotionCountResponse>> emotionStats(@PathVariable Long userId) {
        return ResponseEntity.ok(vibeStatsService.countEmotions(userId));
    }

    @GetMapping("/{userId}/genres")
    public ResponseEntity<List<TopGenreResponse>> genreStats(@PathVariable Long userId) {
        return ResponseEntity.ok(vibeStatsService.topGenres(userId));
    }

    @GetMapping("/{userId}/artists")
    public ResponseEntity<List<TopArtistResponse>> artistStats(@PathVariable Long userId) {
        return ResponseEntity.ok(vibeStatsService.topArtists(userId));
    }

    @GetMapping("/{userId}/timeline")
    public ResponseEntity<List<TimelinePointResponse>> timeline(@PathVariable Long userId) {
        return ResponseEntity.ok(vibeStatsService.timeline(userId));
    }
}
