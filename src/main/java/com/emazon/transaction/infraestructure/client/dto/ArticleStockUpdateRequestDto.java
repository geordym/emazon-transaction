package com.emazon.transaction.infraestructure.client.dto;

public record ArticleStockUpdateRequestDto(Long supplyId, Long articleId, Integer quantity) {
}
