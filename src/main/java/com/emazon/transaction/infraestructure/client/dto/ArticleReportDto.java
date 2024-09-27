package com.emazon.transaction.infraestructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleReportDto {
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private Double total;
}
