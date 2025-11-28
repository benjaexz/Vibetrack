package com.vibetrack.repository;

import com.vibetrack.model.AppUser;
import com.vibetrack.model.VibeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface VibeEntryRepository extends JpaRepository<VibeEntry, Long> {

    // Buscar vibes por userId simples
    List<VibeEntry> findByUserId(Long userId);

    // Buscar todas as vibes de um usuário (ordenadas)
    List<VibeEntry> findByUserOrderByTimestampDesc(AppUser user);

    // Buscar vibes por intervalo de tempo (para relatórios)
    List<VibeEntry> findByUserAndTimestampBetween(AppUser user, Instant start, Instant end);

}
