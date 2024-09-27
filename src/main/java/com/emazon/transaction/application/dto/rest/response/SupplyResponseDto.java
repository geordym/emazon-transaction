package com.emazon.transaction.application.dto.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SupplyResponseDto {
    private Long id;
    private Long articleId;
    private Integer quantity;
    private LocalDate supplyDate;
    private String deliveryStatus;
}
