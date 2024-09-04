package com.emazon.transaction.domain.ports.out;

public interface ArticleServicePort {
    void updateArticle(Long articleId, int quantity);
}
