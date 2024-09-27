package com.emazon.transaction.infraestructure.client.dto;

import com.emazon.transaction.domain.model.dto.shop.PaginationArticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartItemsResponseDto {
    public PaginationArticle pagination;
    public Double total;
}
