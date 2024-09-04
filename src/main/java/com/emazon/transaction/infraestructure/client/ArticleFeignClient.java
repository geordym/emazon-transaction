package com.emazon.transaction.infraestructure.client;

import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateRequestDto;
import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateResponseDto;
import com.emazon.transaction.infraestructure.configuration.feign.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "STOCK-API", url = "${external.stock.api.base-url}", configuration = FeignClientConfig.class)
public interface ArticleFeignClient {

    @PutMapping(value = "/api/articulos/stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    ArticleStockUpdateResponseDto updateStockArticle(@RequestBody ArticleStockUpdateRequestDto articleStockUpdateRequestDto);

}
