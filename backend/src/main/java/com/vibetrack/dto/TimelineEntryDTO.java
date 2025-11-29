package com.vibetrack.dto;

import java.time.LocalDate;

public class TimelineEntryDTO {

    private LocalDate date;
    private Long count;

    public TimelineEntryDTO() {}

    public TimelineEntryDTO(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
    }

    public LocalDate getDate() { return date; }
    public Long getCount() { return count; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setCount(Long count) { this.count = count; }
}
