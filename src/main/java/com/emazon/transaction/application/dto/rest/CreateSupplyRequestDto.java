package com.emazon.transaction.application.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CreateSupplyRequestDto {
    private Long articleId;
    private Integer quantity;
    private LocalDateTime supplyDate;
    private String providerName;
    private BigDecimal cost;
    private String description;

}
