package com.vibetrack.controller;

import com.vibetrack.dto.ApiResponse;
import com.vibetrack.dto.VibeEntryRequest;
import com.vibetrack.dto.VibeEntryResponse;
import com.vibetrack.entity.User;
import com.vibetrack.service.UserService;
import com.vibetrack.service.VibeEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VibeEntryResponse>> create(@RequestBody VibeEntryRequest request) {
        User user = getAuthenticatedUser();
        VibeEntryResponse response = vibeEntryService.create(request, user);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Vibe registrada.", response)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<List<VibeEntryResponse>>> listMyVibes() {
        User user = getAuthenticatedUser();
        List<VibeEntryResponse> list = vibeEntryService.findAllByUser(user);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Listagem feita.", list)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        User user = getAuthenticatedUser();
        vibeEntryService.delete(id, user);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Deletado.", null)
        );
    }
}
