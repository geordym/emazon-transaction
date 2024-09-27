package com.emazon.transaction.infraestructure.client;


import com.emazon.transaction.domain.model.dto.shop.PaginationArticle;
import com.emazon.transaction.infraestructure.client.dto.ArticleResponseDto;
import com.emazon.transaction.infraestructure.client.dto.ShoppingCartItemsResponseDto;
import com.emazon.transaction.infraestructure.configuration.feign.FeignClientConfig;
import io.swagger.annotations.Authorization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(name = "SHOPPINGCART-API", url = "http://localhost:8082", configuration = FeignClientConfig.class)
public interface ShoppingCartFeignClient {

    @GetMapping(value = "/api/shoppingcart/items/list?size=10000", consumes = MediaType.APPLICATION_JSON_VALUE)
    ShoppingCartItemsResponseDto getArticlesShoppingCartById(@RequestHeader("Authorization") String token);


    @GetMapping(value = "/api/shoppingcart/items/clear/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void clearItemsOfShoppingCart(@PathVariable Long customerId);

}
