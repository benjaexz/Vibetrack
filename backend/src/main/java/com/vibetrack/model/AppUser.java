package com.vibetrack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user") // opcional, mas evita conflito de nomes
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username") // garante que o JSON saia com "username"
    private String username;

    @Column(name = "email")
    private String email;

    public AppUser() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void setName(String Sunyuk) {
    }
}
