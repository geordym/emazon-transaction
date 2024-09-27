package com.emazon.transaction.infraestructure.client;


import com.emazon.transaction.application.dto.rest.GenericResponseDto;
import com.emazon.transaction.infraestructure.client.dto.*;
import com.emazon.transaction.infraestructure.configuration.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "STOCK-API", url = "${external.stock.api.base-url}", configuration = FeignClientConfig.class)
public interface StockFeignClient {

    @PutMapping("/api/articulos/stock/decrease")
    GenericResponseDto decreaseStockOfArticles(@RequestBody List<DecreaseArticleStockRequestDto> decreaseArticleStockRequestDto);
    @PutMapping(value = "/api/articulos/stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    ArticleStockUpdateResponseDto updateStockArticle(@RequestBody ArticleStockUpdateRequestDto articleStockUpdateRequestDto);

    @GetMapping(value = "/api/articulos/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<ArticleResponseDto> getArticleById(@PathVariable("articleId") Long articleId);

    @PostMapping("/api/articulos/utilities/calculate-total")
    Double calculateTotalInShoppingCartItemList(@RequestBody List<ShoppingCartItemShortDto> shoppingCartItemShortDtoList);

}