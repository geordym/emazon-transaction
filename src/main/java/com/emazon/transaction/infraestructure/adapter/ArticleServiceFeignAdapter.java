package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.domain.ports.out.ArticleServicePort;
import com.emazon.transaction.infraestructure.client.ArticleFeignClient;
import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateRequestDto;
import com.emazon.transaction.infraestructure.client.dto.ArticleStockUpdateResponseDto;
import com.emazon.transaction.infraestructure.schedule.SupplyRetrySchedulerService;
import feign.FeignException;
import feign.RetryableException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class ArticleServiceFeignAdapter implements ArticleServicePort {

    private final ArticleFeignClient articleFeignClient;
    private final SupplyRetrySchedulerService supplyRetrySchedulerService;


    @Async("threadPoolExecutor")
    @Override
    public void updateArticle(Long supplyId, Long articleId, int quantity) {
        ArticleStockUpdateRequestDto articleStockUpdateRequestDto = new ArticleStockUpdateRequestDto(supplyId, articleId, quantity);
        try{
            ArticleStockUpdateResponseDto articleStockUpdateResponseDto = articleFeignClient.updateStockArticle(articleStockUpdateRequestDto);
            System.out.println(articleStockUpdateResponseDto.getMessage());

        }catch (RetryableException retryableException){
            supplyRetrySchedulerService.startRetryTask();
        }

    }


}
