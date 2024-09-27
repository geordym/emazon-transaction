package com.emazon.transaction.infraestructure.configuration;

import com.emazon.transaction.domain.ports.out.auth.UserAuthenticationPort;
import com.emazon.transaction.domain.ports.out.security.TokenProviderPort;
import com.emazon.transaction.infraestructure.adapter.auth.UserAuthenticationSpringSecurityAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationBeanConfiguration {
    @Bean
    public UserAuthenticationPort userAuthenticationPort(TokenProviderPort tokenProviderPort){

        return new UserAuthenticationSpringSecurityAdapter(tokenProviderPort);
    }
}
