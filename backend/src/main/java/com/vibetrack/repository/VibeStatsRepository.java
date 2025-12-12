package com.vibetrack.repository;

import com.vibetrack.entity.VibeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VibeStatsRepository extends JpaRepository<VibeEntry, Long> {

    @Query("""
        SELECT v.genre AS genre, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId
        GROUP BY v.genre
    """)
    List<Object[]> topGenres(Long userId);

    @Query("""
        SELECT v.emotion AS emotion, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId
        GROUP BY v.emotion
    """)
    List<Object[]> countByEmotion(Long userId);

    @Query("""
        SELECT v.artist AS artist, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId
        GROUP BY v.artist
    """)
    List<Object[]> topArtists(Long userId);

    @Query("""
        SELECT v.timestamp AS timestamp, COUNT(v) AS total
        FROM VibeEntry v
        WHERE v.user.id = :userId
        GROUP BY v.timestamp
        ORDER BY v.timestamp
    """)
    List<Object[]> timeline(Long userId);
}
