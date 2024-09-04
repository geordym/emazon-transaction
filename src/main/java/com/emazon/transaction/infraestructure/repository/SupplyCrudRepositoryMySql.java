package com.emazon.transaction.infraestructure.repository;

import com.emazon.transaction.infraestructure.entities.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyCrudRepositoryMySql extends JpaRepository<SupplyEntity, Long> {
}
