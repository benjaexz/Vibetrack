package com.vibetrack.repository;

import com.vibetrack.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<Object> findByEmail(String email);
}

