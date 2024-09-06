package com.emazon.transaction.infraestructure.adapter.security;

import com.emazon.transaction.domain.ports.out.security.PasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class EncryptionBCryptAdapter implements PasswordEncoderPort {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}