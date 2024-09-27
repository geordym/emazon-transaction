package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.domain.exception.util.ArticleConnectionFailure;
import com.emazon.transaction.domain.exception.util.ArticleWithoutStockException;
import com.emazon.transaction.domain.exception.util.ConnectionExternalException;
import com.emazon.transaction.domain.ports.out.external.StockConnectionPort;
import com.emazon.transaction.infraestructure.client.StockFeignClient;
import com.emazon.transaction.infraestructure.client.dto.*;
import com.emazon.transaction.infraestructure.schedule.SupplyRetrySchedulerService;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class StockConnectionFeignAdapter implements StockConnectionPort {

    private final StockFeignClient stockFeignClient;
    private final SupplyRetrySchedulerService supplyRetrySchedulerService;


    @Override
    public void communicateDecreaseOfArticles(Map<Long, Integer> articlesToDecrease) {
        List<DecreaseArticleStockRequestDto> stockRequestDtoList = articlesToDecrease.entrySet()
                .stream()
                .map(entry -> {
                    DecreaseArticleStockRequestDto dto = new DecreaseArticleStockRequestDto();
                    dto.setArticleId(entry.getKey());
                    dto.setQuantity(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList());

      try{
          stockFeignClient.decreaseStockOfArticles(stockRequestDtoList);
      }catch (FeignException.Conflict e){
          throw new ArticleWithoutStockException();
      }catch (FeignException e){
          throw new ConnectionExternalException();
      }
    }

    @Async("threadPoolExecutor")
    @Override
    public void updateArticle(Long supplyId, Long articleId, int quantity) {
        ArticleStockUpdateRequestDto articleStockUpdateRequestDto = new ArticleStockUpdateRequestDto(supplyId, articleId, quantity);
        try{
            ArticleStockUpdateResponseDto articleStockUpdateResponseDto = stockFeignClient.updateStockArticle(articleStockUpdateRequestDto);
            System.out.println(articleStockUpdateResponseDto.getMessage());

        }catch (RetryableException retryableException){
            supplyRetrySchedulerService.startRetryTask();
        }

    }

    @Override
    public boolean existsArticle(Long articleId) {
        try{
            Optional<ArticleResponseDto> articleResponseDtoOptional = stockFeignClient.getArticleById(articleId);
            return articleResponseDtoOptional.isPresent();
        }catch (FeignException e){
            throw new ArticleConnectionFailure();
        }
    }

    @Override
    public Double calculateTotalOfArticles(Map<Long, Integer> articles) {
        List<ShoppingCartItemShortDto> shoppingCartItemShortDto = articles.entrySet()
                .stream()
                .map(entry -> new ShoppingCartItemShortDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return stockFeignClient.calculateTotalInShoppingCartItemList(shoppingCartItemShortDto);
    }

}
