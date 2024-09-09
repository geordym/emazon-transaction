package com.emazon.transaction.infraestructure.util;

import com.emazon.transaction.infraestructure.exception.InvalidSupplyIdException;
import org.springframework.stereotype.Component;

@Component
public class ValidateSupplyId {

    public void validateSupplyId(String supplyId) {
        try {
            Long parsedSupplyId = Long.parseLong(supplyId);
            if (parsedSupplyId <= 0) {
                throw new InvalidSupplyIdException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidSupplyIdException();
        }
    }
}
