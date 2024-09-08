package com.emazon.transaction.infraestructure.repository;

import com.emazon.transaction.infraestructure.entities.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplyCrudRepositoryMySql extends JpaRepository<SupplyEntity, Long> {

    @Modifying
    @Query("UPDATE SupplyEntity s SET s.status = :newStatus" +
            " WHERE s.id = :supplyId")
    void updateSupplyStatus(Long supplyId, String newStatus);

    @Modifying
    @Query("SELECT s FROM SupplyEntity s WHERE s.status = :status")
    List<SupplyEntity> findSuppliesByStatus(@Param("status") String status);

}
