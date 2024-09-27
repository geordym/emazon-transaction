package com.emazon.transaction.domain.ports.out.external;

import com.emazon.transaction.domain.model.dto.shop.PaginationArticle;

public interface ShoppingCartConnectionPort {
    PaginationArticle getArticlesOfShoppingCartByCustomer(String authorization);
    void deleteAllItemsOfShoppingCart(Long customerId);
}
