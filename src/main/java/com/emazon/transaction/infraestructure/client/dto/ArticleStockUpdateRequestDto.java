package com.emazon.transaction.infraestructure.client.dto;

public record ArticleStockUpdateRequestDto(Long articleId, Integer quantity) {
}
