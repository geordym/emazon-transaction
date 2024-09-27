package com.emazon.transaction.domain.ports.out.auth;

public interface UserAuthenticationPort {

    Long getAuthenticatedWarehouseId();
    Long getAuthenticatedCustomerId();
    String getTokenOfAuthenticatedUser();

    String getEmailofAuthenticatedUser();

}
