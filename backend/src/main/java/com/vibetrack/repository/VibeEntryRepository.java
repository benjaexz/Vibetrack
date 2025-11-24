package com.vibetrack.repository;

import com.vibetrack.model.Emotion;
import com.vibetrack.model.VibeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VibeEntryRepository extends JpaRepository<VibeEntry, Long> {

    @Query("SELECT v.emocao as emotion, COUNT(v) as count FROM VibeEntry v GROUP BY v.emocao")
    List<Object[]> countByEmotion();

    @Query("SELECT v.genero as name, COUNT(v) as count FROM VibeEntry v GROUP BY v.genero ORDER BY COUNT(v) DESC")
    List<Object[]> findTopGenres();

    @Query("SELECT v.artista as name, COUNT(v) as count FROM VibeEntry v GROUP BY v.artista ORDER BY COUNT(v) DESC")
    List<Object[]> findTopArtists();

    @Query("SELECT v FROM VibeEntry v WHERE v.timestamp >= :startDate ORDER BY v.timestamp ASC")
    List<VibeEntry> findVibesAfterDate(@Param("startDate") Instant startDate);

    List<VibeEntry> findByUserId(Long userId);
}