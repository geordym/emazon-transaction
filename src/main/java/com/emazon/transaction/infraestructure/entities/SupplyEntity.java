package com.emazon.transaction.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
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
    private LocalDateTime supplyDate;
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

    @Column(name = "status", nullable = false)
    private String status;



}
