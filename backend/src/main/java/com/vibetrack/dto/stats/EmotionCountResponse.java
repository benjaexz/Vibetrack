package com.vibetrack.dto.stats;

import com.vibetrack.model.Emotion;

public record EmotionCountResponse(
        Emotion emotion,
        Long total
) {}
