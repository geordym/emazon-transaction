package com.emazon.transaction.application.handler;

import com.emazon.transaction.application.dto.rest.GenericResponseDto;

public interface IShopRestHandler {
    GenericResponseDto buyItemsInCart();
}
