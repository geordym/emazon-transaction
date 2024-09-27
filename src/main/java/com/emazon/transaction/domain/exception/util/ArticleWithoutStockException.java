package com.emazon.transaction.domain.exception.util;

import java.time.LocalDateTime;

public class ArticleWithoutStockException extends ErrorException{

    public ArticleWithoutStockException() {
        super(ExceptionConstants.ARTICLE_WITHOUT_STOCK_ERROR, ExceptionConstants.ARTICLE_WITHOUT_STOCK_MESSAGE, LocalDateTime.now().toString());
    }
}
