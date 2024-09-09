package com.emazon.transaction.domain.exception.util;

import java.time.LocalDateTime;

import static com.emazon.transaction.domain.exception.util.ExceptionConstants.ARTICLE_NOT_FOUND_ERROR_MESSAGE;

public class ArticleNotFoundException extends ErrorException {
    public ArticleNotFoundException() {
        super(ExceptionConstants.ARTICLE_NOT_FOUND_ERROR, ARTICLE_NOT_FOUND_ERROR_MESSAGE, LocalDateTime.now().toString());
    }

}
