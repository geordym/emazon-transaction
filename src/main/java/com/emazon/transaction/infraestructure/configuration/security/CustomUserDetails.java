package com.emazon.transaction.infraestructure.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final Long userId;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean isActive;

    public CustomUserDetails(Long userId, String username, String password, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = Collections.emptyList();
        this.isActive = isActive;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Define tu lógica aquí
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Define tu lógica aquí
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Define tu lógica aquí
    }

    @Override
    public boolean isEnabled() {
        return isActive; // Define tu lógica aquí
    }
}