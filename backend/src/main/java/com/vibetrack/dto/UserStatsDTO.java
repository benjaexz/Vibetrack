package com.vibetrack.dto;

import java.util.List;
import java.util.Map;

public class UserStatsDTO {

    private Map<String, Long> topGenres;
    private Map<String, Long> topArtists;
    private Map<String, Long> emotions;
    private List<TimelineEntryDTO> timeline;

    public UserStatsDTO() {}

    public UserStatsDTO(Map<String, Long> topGenres,
                        Map<String, Long> topArtists,
                        Map<String, Long> emotions,
                        List<TimelineEntryDTO> timeline) {
        this.topGenres = topGenres;
        this.topArtists = topArtists;
        this.emotions = emotions;
        this.timeline = timeline;
    }

    public Map<String, Long> getTopGenres() { return topGenres; }
    public Map<String, Long> getTopArtists() { return topArtists; }
    public Map<String, Long> getEmotions() { return emotions; }
    public List<TimelineEntryDTO> getTimeline() { return timeline; }

    public void setTopGenres(Map<String, Long> topGenres) { this.topGenres = topGenres; }
    public void setTopArtists(Map<String, Long> topArtists) { this.topArtists = topArtists; }
    public void setEmotions(Map<String, Long> emotions) { this.emotions = emotions; }
    public void setTimeline(List<TimelineEntryDTO> timeline) { this.timeline = timeline; }
}
