package com.vibetrack.security;

import com.vibetrack.model.AppUser;
import com.vibetrack.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = (AppUser) appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Converter AppUser em um UserDetails do Spring
        return User.builder()
                .username(user.getEmail())      // Spring usa email como username
                .password(user.getPassword())  // senha criptografada
                .authorities("USER")           // pode ser ROLE_USER depois
                .build();
    }
}
