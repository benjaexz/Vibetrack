package com.vibetrack.controller;

import com.vibetrack.model.VibeEntry;
import com.vibetrack.service.VibeEntryService;
import com.vibetrack.dto.VibeEntryRequest;
import com.vibetrack.model.Emotion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/vibes")
@CrossOrigin(origins = "http://localhost:4200") // mantém consistência com seu front
public class VibeEntryController {

    private final VibeEntryService service;

    public VibeEntryController(VibeEntryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VibeEntry> create(@RequestBody VibeEntryRequest req) {
        Instant ts = req.timestamp == null ? null : Instant.parse(req.timestamp);
        VibeEntry created = service.createVibe(req.userId, req.musica, req.artista, req.genero, req.emocao == null ? Emotion.NEUTRAL : req.emocao, ts);
        return ResponseEntity.ok(created);
    }



    @GetMapping
    public ResponseEntity<List<VibeEntry>> list(@RequestParam(required = false) Long userId) {
        List<VibeEntry> list = (userId == null) ? service.listAll() : service.listByUser(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VibeEntry> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VibeEntry> update(
            @PathVariable Long id,
            @RequestBody VibeEntryRequest req
    ) {
        VibeEntry updated = service.updateVibe(
                id,
                req.userId,
                req.musica,
                req.artista,
                req.genero,
                req.emocao,
                req.timestamp == null ? null : Instant.parse(req.timestamp)
        );

        return ResponseEntity.ok(updated);
    }

}
