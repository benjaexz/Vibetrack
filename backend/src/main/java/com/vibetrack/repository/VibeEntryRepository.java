package com.vibetrack.repository;

import com.vibetrack.model.VibeEntry;
import com.vibetrack.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface VibeEntryRepository extends JpaRepository<VibeEntry, Long> {

    // Buscar todas as vibes de um usuário
    List<VibeEntry> findByUserOrderByTimestampDesc(AppUser user);

    // Buscar vibes por intervalo de tempo (útil para estatísticas)
    List<VibeEntry> findByUserAndTimestampBetween(AppUser user, Instant start, Instant end);
}
