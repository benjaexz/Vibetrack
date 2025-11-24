package com.vibetrack.dto;

public class TopItemDTO {

    private String name;
    private Long count;

    // Construtor vazio
    public TopItemDTO() {
    }

    // Construtor com parâmetros
    public TopItemDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}