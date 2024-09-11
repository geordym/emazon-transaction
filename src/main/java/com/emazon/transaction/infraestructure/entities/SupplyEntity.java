package com.emazon.transaction.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "supply")
@Getter
@Setter
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_id")
    private Long articleId;
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "supply_date", nullable = false)
    private LocalDate supplyDate;

    @Column(name = "received_at")
    private LocalDate receivedDate;

    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "description")
    private String description;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "created_by_auxiliary_id")
    private Long createdByAuxiliaryId;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "sync_status", nullable = false)
    private String syncStatus;

    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus;


}
