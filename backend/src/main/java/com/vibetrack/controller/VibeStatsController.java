package com.vibetrack.controller;

import com.vibetrack.dto.UserStatsDTO;
import com.vibetrack.service.VibeEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stats")
public class VibeStatsController {

    private final VibeEntryService vibeEntryService;

    public VibeStatsController(VibeEntryService vibeEntryService) {
        this.vibeEntryService = vibeEntryService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserStatsDTO> getStats(@PathVariable Long userId) {
        UserStatsDTO stats = vibeEntryService.getUserStats(userId);
        return ResponseEntity.ok(stats);
    }
}
