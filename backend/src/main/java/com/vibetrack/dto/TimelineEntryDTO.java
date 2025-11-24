package com.vibetrack.dto;

import java.time.LocalDate;

public class TimelineEntryDTO {

    private LocalDate date;
    private Long count;

    // Construtor vazio
    public TimelineEntryDTO() {
    }

    // Construtor com parâmetros
    public TimelineEntryDTO(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
    }

    // Getters e Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}