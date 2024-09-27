package com.emazon.transaction.domain.model.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleShoppingCartDto {
    private Long id;
    private String name;
    private String description;
    private Integer quantityInStock;
    private Integer quantityInCart;
    private Double unitPrice;
    private List<Category> categories;
    private String mark;
    private boolean areStock;
    private LocalDateTime nextSupplyDate;

}
