package com.vibetrack.dto;

import com.vibetrack.model.Emotion;

public class EmotionStatsDTO {

    private Emotion emotion;
    private Long count;

    // Construtor vazio (necessário para serialização)
    public EmotionStatsDTO() {
    }

    // Construtor com parâmetros
    public EmotionStatsDTO(Emotion emotion, Long count) {
        this.emotion = emotion;
        this.count = count;
    }

    // Getters e Setters
    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}