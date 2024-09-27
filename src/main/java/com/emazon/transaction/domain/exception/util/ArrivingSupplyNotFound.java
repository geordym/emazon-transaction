package com.emazon.transaction.domain.exception.util;

import java.time.LocalDateTime;

public class ArrivingSupplyNotFound extends ErrorException{


    public ArrivingSupplyNotFound() {
        super(ExceptionConstants.ARRIVING_SUPPLY_NOT_FOUND_ERROR, ExceptionConstants.ARRIVING_SUPPLY_NOT_FOUND_MESSAGE, LocalDateTime.now().toString());
    }
}
