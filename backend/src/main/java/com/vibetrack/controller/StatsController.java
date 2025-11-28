package com.vibetrack.controller;

import com.vibetrack.dto.EmotionStatsDTO;
import com.vibetrack.dto.TimelineEntryDTO;
import com.vibetrack.dto.TopItemDTO;
import com.vibetrack.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    // Timeline de emoções por dia
    @GetMapping("/timeline/{userId}")
    public List<TimelineEntryDTO> getTimeline(@PathVariable Long userId) {
        return statsService.getTimeline(userId);
    }

    // Top gêneros
    @GetMapping("/top/generos/{userId}")
    public List<TopItemDTO> getTopGeneros(@PathVariable Long userId) {
        return statsService.getTop(userId, "genero");
    }

    // Top artistas
    @GetMapping("/top/artistas/{userId}")
    public List<TopItemDTO> getTopArtistas(@PathVariable Long userId) {
        return statsService.getTop(userId, "artista");
    }

    // Estatísticas de emoções
    @GetMapping("/emocoes/{userId}")
    public List<EmotionStatsDTO> getEmotionStats(@PathVariable Long userId) {
        return statsService.getEmotionStats(userId);
    }
}
