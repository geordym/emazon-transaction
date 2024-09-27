package com.emazon.transaction.domain.model.dto.shop;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PaginationArticle {
    private List<ArticleShoppingCartDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

}