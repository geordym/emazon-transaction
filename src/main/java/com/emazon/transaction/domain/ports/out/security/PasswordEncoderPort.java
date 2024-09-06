package com.emazon.transaction.domain.ports.out.security;

public interface PasswordEncoderPort {

    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);

}
