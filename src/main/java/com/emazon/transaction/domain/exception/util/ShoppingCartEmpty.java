package com.emazon.transaction.domain.exception.util;

import java.time.LocalDateTime;

import static com.emazon.transaction.domain.exception.util.ExceptionConstants.SUPPLY_NOT_FOUND_ERROR;
import static com.emazon.transaction.domain.exception.util.ExceptionConstants.SUPPLY_NOT_FOUND_ERROR_MESSAGE;

public class ShoppingCartEmpty extends ErrorException{

    public ShoppingCartEmpty() {
        super(ExceptionConstants.SHOPPINGCART_EMPTY_ERROR, ExceptionConstants.SHOPPINGCART_EMPTY_MESSAGE, LocalDateTime.now().toString());
    }
}
