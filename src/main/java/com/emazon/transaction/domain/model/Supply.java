package com.emazon.transaction.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Supply {
    private Long id;
    private Long articleId;
    private Integer quantity;
    private LocalDate supplyDate;
    private LocalDate receivedAt;
    private String providerName;
    private BigDecimal cost;
    private String description;
    private Long createdByAuxiliaryId;
    private LocalDateTime createdDate;
    private String syncStatus;
    private String deliveryStatus;

    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", quantity=" + quantity +
                ", supplyDate=" + supplyDate +
                ", receivedAt=" + receivedAt +
                ", providerName='" + providerName + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", createdByAuxiliaryId=" + createdByAuxiliaryId +
                ", createdDate=" + createdDate +
                ", syncStatus='" + syncStatus + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
