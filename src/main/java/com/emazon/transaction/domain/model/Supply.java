package com.emazon.transaction.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Supply {
    private Long id;
    private Long articleId;
    private Integer quantity;
    private LocalDateTime supplyDate;
    private String providerName;
    private BigDecimal cost;
    private String description;
    private Long createdByAuxiliaryId;
    private LocalDateTime createdDate;
    private String status;
}
