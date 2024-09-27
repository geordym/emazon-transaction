package com.emazon.transaction.domain.ports.out.external;

import java.util.Map;

public interface StockConnectionPort {

    void communicateDecreaseOfArticles(Map<Long, Integer> articlesToDecrease);

    void updateArticle(Long supplyId, Long articleId, int quantity);
    boolean existsArticle(Long articleId);

    Double calculateTotalOfArticles(Map<Long, Integer> articles);

}
