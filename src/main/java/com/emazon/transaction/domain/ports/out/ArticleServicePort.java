package com.emazon.transaction.domain.ports.out;

public interface ArticleServicePort {
    void updateArticle(Long supplyId, Long articleId, int quantity);
}
