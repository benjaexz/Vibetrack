package com.vibetrack.controller;

import com.vibetrack.dto.EmotionStatsDTO;
import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "http://localhost:4200")
public class StatsController {

    @Autowired
    private StatsService statsService;

    // GET /api/stats/emotions - Estatísticas por emoção
    @GetMapping("/emotions")
    public ResponseEntity<List<EmotionStatsDTO>> getEmotionStats() {
        List<EmotionStatsDTO> stats = statsService.getEmotionStats();
        return ResponseEntity.ok(stats);
    }

    // GET /api/stats/genres - Top 3 gêneros
    @GetMapping("/genres")
    public ResponseEntity<List<TopItemDTO>> getTopGenres() {
        List<TopItemDTO> topGenres = statsService.getTopGenres(3);
        return ResponseEntity.ok(topGenres);
    }

    // GET /api/stats/artists - Top 3 artistas
    @GetMapping("/artists")
    public ResponseEntity<List<TopItemDTO>> getTopArtists() {
        List<TopItemDTO> topArtists = statsService.getTopArtists(3);
        return ResponseEntity.ok(topArtists);
    }

    // GET /api/stats/timeline?days=7 - Timeline dos últimos N dias
    @GetMapping("/timeline")
    public ResponseEntity<List<TimelineEntryDTO>> getTimeline(
            @RequestParam(defaultValue = "7") int days) {
        List<TimelineEntryDTO> timeline = statsService.getTimeline(days);
        return ResponseEntity.ok(timeline);
    }
}