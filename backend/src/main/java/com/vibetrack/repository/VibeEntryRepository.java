package com.vibetrack.repository;

import com.vibetrack.entity.User;
import com.vibetrack.entity.VibeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface VibeEntryRepository extends JpaRepository<VibeEntry, Long> {

    // Buscar todas as vibes pelo ID do usuário
    List<VibeEntry> findByUserId(Long userId);

    // Buscar todas as vibes ordenadas por data
    List<VibeEntry> findByUserOrderByTimestampDesc(User user);

    // Buscar vibes dentro de um intervalo de tempo
    List<VibeEntry> findByUserAndTimestampBetween(User user, Instant start, Instant end);

    // Contar vibes do usuário
    long countByUser(User user);
}
