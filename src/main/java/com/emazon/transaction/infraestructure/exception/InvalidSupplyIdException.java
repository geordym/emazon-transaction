package com.emazon.transaction.infraestructure.exception;

import com.emazon.transaction.domain.exception.util.ErrorException;

import java.time.LocalDateTime;

public class InvalidSupplyIdException extends ErrorException {


    public InvalidSupplyIdException() {
        super(ExceptionConstants.INVALID_SUPPLYID_ERROR, ExceptionConstants.INVALID_SUPPLYID_ERROR_MESSAGE, LocalDateTime.now().toString());
    }
}
