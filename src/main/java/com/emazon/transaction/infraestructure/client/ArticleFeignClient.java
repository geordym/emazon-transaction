package com.emazon.transaction.infraestructure.client;

import com.emazon.transaction.infraestructure.client.dto.ArticleResponseDto;
import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateRequestDto;
import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateResponseDto;
import com.emazon.transaction.infraestructure.configuration.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "STOCK-API", url = "${external.stock.api.base-url}", configuration = FeignClientConfig.class)
public interface ArticleFeignClient {

    @PutMapping(value = "/api/articulos/stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    ArticleStockUpdateResponseDto updateStockArticle(@RequestBody ArticleStockUpdateRequestDto articleStockUpdateRequestDto);

    @GetMapping(value = "/api/articulos/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<ArticleResponseDto> getArticleById(@PathVariable("articleId") Long articleId);


}
