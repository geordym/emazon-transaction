package com.emazon.transaction.infraestructure.adapter.auth;

import com.emazon.transaction.domain.ports.out.auth.UserAuthenticationPort;
import com.emazon.transaction.domain.ports.out.security.TokenProviderPort;
import com.emazon.transaction.infraestructure.configuration.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
public class UserAuthenticationSpringSecurityAdapter implements UserAuthenticationPort {

    private final TokenProviderPort tokenProviderPort;
    @Override
    public Long getAuthenticatedWarehouseId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf(customUserDetails.getUserId());
    }

    @Override
    public Long getAuthenticatedCustomerId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf(customUserDetails.getUserId());
    }

    @Override
    public String getTokenOfAuthenticatedUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            String authHeader = attributes.getRequest().getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                return authHeader;
            }
        }
        return null;
    }

    @Override
    public String getEmailofAuthenticatedUser() {
        String tokenRequest = getTokenOfAuthenticatedUser();
        String token = tokenRequest;
        if (tokenRequest != null && tokenRequest.startsWith("Bearer ")) {
            token = tokenRequest.substring(7);
        }

        String email = (String) tokenProviderPort.extractClaim(token, "email");
        return email;
    }

}
