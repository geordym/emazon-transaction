package com.emazon.transaction.domain.exception.util;

import java.time.LocalDateTime;

public class ConnectionExternalException extends ErrorException{

    public ConnectionExternalException() {
        super(ExceptionConstants.SHOPPINGCART_EMPTY_ERROR, ExceptionConstants.SHOPPINGCART_EMPTY_MESSAGE, LocalDateTime.now().toString());
    }
}
