package com.emazon.transaction.infraestructure.repository;

import com.emazon.transaction.infraestructure.entities.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SupplyCrudRepositoryMySql extends JpaRepository<SupplyEntity, Long> {

    @Modifying
    @Query("UPDATE SupplyEntity s SET s.syncStatus = :newStatus" +
            " WHERE s.id = :supplyId")
    void updateSupplySyncStatus(Long supplyId, String newStatus);

    @Modifying
    @Query("SELECT s FROM SupplyEntity s WHERE s.syncStatus = :status")
    List<SupplyEntity> findSuppliesBySyncStatus(@Param("status") String status);

    @Query("SELECT s FROM SupplyEntity s WHERE" +
            " s.articleId = :articleId " +
            "AND s.deliveryStatus = :status ORDER BY s.supplyDate ASC LIMIT 1")
    Optional<SupplyEntity> findClosestInTransitSupplyByArticleId(@Param("status") String InTransitStatus, @Param("articleId") Long articleId);

}
