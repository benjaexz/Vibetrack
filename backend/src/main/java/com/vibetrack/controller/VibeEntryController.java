package com.vibetrack.controller;

import com.vibetrack.dto.VibeEntryRequest;
import com.vibetrack.dto.VibeEntryResponse;
import com.vibetrack.model.AppUser;
import com.vibetrack.service.UserService;
import com.vibetrack.service.VibeEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vibes")
public class VibeEntryController {

    private final VibeEntryService vibeEntryService;
    private final UserService userService;

    public VibeEntryController(VibeEntryService vibeEntryService, UserService userService) {
        this.vibeEntryService = vibeEntryService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<VibeEntryResponse> create(
            @AuthenticationPrincipal AppUser authUser,
            @RequestBody VibeEntryRequest request
    ) {
        VibeEntryResponse response = vibeEntryService.create(authUser.getId(), request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VibeEntryResponse>> listByUser(
            @AuthenticationPrincipal AppUser authUser
    ) {
        List<VibeEntryResponse> list = vibeEntryService.findAllByUser(authUser.getId());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal AppUser authUser,
            @PathVariable Long id
    ) {
        vibeEntryService.delete(id, authUser.getId());
        return ResponseEntity.noContent().build();
    }
}
