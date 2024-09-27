package com.emazon.transaction.application.handler.implementations;

import com.emazon.transaction.application.dto.rest.GenericResponseDto;
import com.emazon.transaction.application.handler.IShopRestHandler;
import com.emazon.transaction.domain.ports.in.ShopUseCases;
import com.emazon.transaction.infraestructure.rest.constants.ResponseConstantes;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ShopRestHandler implements IShopRestHandler {
    private final ShopUseCases shopUseCases;

    @Override
    public GenericResponseDto buyItemsInCart() {
         shopUseCases.buyItemsInCart();
         return new GenericResponseDto(ResponseConstantes.ITEM_BOUGH_SUCCESS_MESSAGE);
    }


}
