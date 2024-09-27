package com.emazon.transaction.infraestructure.rest;


import com.emazon.transaction.application.dto.rest.GenericResponseDto;
import com.emazon.transaction.application.handler.IShopRestHandler;
import com.emazon.transaction.application.handler.implementations.ShopRestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShoppingController {

    private final IShopRestHandler shopRestHandler;
    @PostMapping("/buy")
    public ResponseEntity<GenericResponseDto> buy(){
            GenericResponseDto genericResponseDto = shopRestHandler.buyItemsInCart();
            return ResponseEntity.ok(genericResponseDto);
    }

}
