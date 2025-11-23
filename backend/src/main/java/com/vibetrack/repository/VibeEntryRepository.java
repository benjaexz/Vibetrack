package com.vibetrack.repository;

import com.vibetrack.model.VibeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VibeEntryRepository extends JpaRepository<VibeEntry, Long> {

    // Busca todas as vibes de um usuário específico
    List<VibeEntry> findByUserId(Long userId);

}
