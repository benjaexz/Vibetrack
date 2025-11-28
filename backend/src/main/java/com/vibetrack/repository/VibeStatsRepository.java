package com.vibetrack.repository;

import com.vibetrack.model.Emotion;
import com.vibetrack.model.VibeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VibeStatsRepository extends JpaRepository<VibeEntry, Long> {

    @Query("""
        SELECT v.emocao AS emotion, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId
        GROUP BY v.emocao
    """)
    List<Object[]> countByEmotion(@Param("userId") Long userId);

    @Query("""
        SELECT v.genero AS genero, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId AND v.genero IS NOT NULL
        GROUP BY v.genero
        ORDER BY total DESC
    """)
    List<Object[]> topGeneros(@Param("userId") Long userId);

    @Query("""
        SELECT v.artista AS artista, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId AND v.artista IS NOT NULL
        GROUP BY v.artista
        ORDER BY total DESC
    """)
    List<Object[]> topArtistas(@Param("userId") Long userId);

    @Query("""
        SELECT DATE(v.timestamp) AS dia, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId
        GROUP BY DATE(v.timestamp)
        ORDER BY dia ASC
    """)
    List<Object[]> timeline(@Param("userId") Long userId);
}
