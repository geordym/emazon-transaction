package com.emazon.transaction.application.dto.rest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CreateSupplyRequestDto {

    @NotNull(message = "Article ID cannot be null")
    @Positive(message = "Article ID must be a positive number")
    private Long articleId;

    @NotNull(message = "Quantity cannot be null")
    @PositiveOrZero(message = "Quantity must be zero or a positive number")
    private Integer quantity;

    @NotNull(message = "Supply Date cannot be null")
    private LocalDate supplyDate;

    @NotBlank(message = "Provider Name cannot be blank")
    @Size(max = 100, message = "Provider Name cannot exceed 100 characters")
    private String providerName;

    @NotNull(message = "Cost cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Cost must be greater than zero")
    private BigDecimal cost;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
}
