package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.domain.model.dto.shop.PaginationArticle;
import com.emazon.transaction.domain.ports.out.external.ShoppingCartConnectionPort;
import com.emazon.transaction.infraestructure.client.ShoppingCartFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShoppingCartConnectionFeignAdapter implements ShoppingCartConnectionPort {

    private final ShoppingCartFeignClient shoppingCartFeignClient;
    @Override
    public PaginationArticle getArticlesOfShoppingCartByCustomer(String authorization) {
        return shoppingCartFeignClient.getArticlesShoppingCartById(authorization).getPagination();
    }

    @Override
    public void deleteAllItemsOfShoppingCart(Long customerId) {
        shoppingCartFeignClient.clearItemsOfShoppingCart(customerId);
    }
}
