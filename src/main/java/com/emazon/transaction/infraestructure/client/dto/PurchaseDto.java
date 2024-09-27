package com.emazon.transaction.infraestructure.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseDto {
    private String emailCustomer;
    private String datetimeBuy;
    private Double totalCost;
    private List<ArticleReportDto> articleList;
}