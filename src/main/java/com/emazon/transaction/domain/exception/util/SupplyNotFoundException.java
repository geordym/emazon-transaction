package com.emazon.transaction.domain.exception.util;


import java.time.LocalDateTime;

import static com.emazon.transaction.domain.exception.util.ExceptionConstants.SUPPLY_NOT_FOUND_ERROR;
import static com.emazon.transaction.domain.exception.util.ExceptionConstants.SUPPLY_NOT_FOUND_ERROR_MESSAGE;

public class SupplyNotFoundException extends ErrorException {

    public SupplyNotFoundException() {
        super(SUPPLY_NOT_FOUND_ERROR, SUPPLY_NOT_FOUND_ERROR_MESSAGE, LocalDateTime.now().toString());
    }

}
