package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.domain.ports.out.ArticleServicePort;
import com.emazon.transaction.infraestructure.client.ArticleFeignClient;
import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateRequestDto;
import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleServiceFeignAdapter implements ArticleServicePort {

    private final ArticleFeignClient articleFeignClient;

    @Override
    public void updateArticle(Long articleId, int quantity) {
        ArticleStockUpdateRequestDto articleStockUpdateRequestDto = new ArticleStockUpdateRequestDto(articleId, quantity);
        ArticleStockUpdateResponseDto articleStockUpdateResponseDto = articleFeignClient.updateStockArticle(articleStockUpdateRequestDto);
    }

}
