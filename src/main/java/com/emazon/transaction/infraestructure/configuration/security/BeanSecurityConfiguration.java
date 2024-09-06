package com.emazon.transaction.infraestructure.configuration.security;

import com.emazon.transaction.domain.ports.out.security.TokenProviderPort;
import com.emazon.transaction.infraestructure.adapter.security.JwtIOTokenAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanSecurityConfiguration {

    @Bean
    public TokenProviderPort tokenProviderPort(){
        return new JwtIOTokenAdapter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
