package com.vibetrack.repository;

import com.vibetrack.entity.AppUser;
import com.vibetrack.entity.VibeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface VibeEntryRepository extends JpaRepository<VibeEntry, Long> {

    // Buscar vibes por ID do usuário (versão simples)
    List<VibeEntry> findByUserId(Long userId);

    // Buscar vibes do usuário ordenadas por data desc
    List<VibeEntry> findByUserOrderByTimestampDesc(AppUser user);

    // Buscar vibes dentro de um intervalo de tempo
    List<VibeEntry> findByUserAndTimestampBetween(AppUser user, Instant start, Instant end);

    // Contar todas as vibes do usuário
    long countByUser(AppUser user);
}
