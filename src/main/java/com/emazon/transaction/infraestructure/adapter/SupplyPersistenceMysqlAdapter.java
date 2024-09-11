package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.application.services.mapper.SupplyMapper;
import com.emazon.transaction.domain.enums.SyncStatus;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import com.emazon.transaction.infraestructure.entities.SupplyEntity;
import com.emazon.transaction.infraestructure.repository.SupplyCrudRepositoryMySql;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SupplyPersistenceMysqlAdapter implements SupplyPersistencePort {
    private final SupplyCrudRepositoryMySql supplyCrudRepositoryMySql;
    @Override
    public Supply saveSupply(Supply supply) {
        SupplyEntity supplyEntity = SupplyMapper.domainToEntity(supply);
        Supply supplyModel = SupplyMapper.entityToDomain(supplyCrudRepositoryMySql.save(supplyEntity));
        return supplyModel;
    }

    @Override
    public List<Supply> getSyncPendingSupplies(String status) {
        List<SupplyEntity> supplyEntities = supplyCrudRepositoryMySql.findSuppliesBySyncStatus(status);
        List<Supply> supplies = supplyEntities.stream().map(supplyEntity -> {
         return SupplyMapper.entityToDomain(supplyEntity)  ;
        }).toList();

        return supplies;
    }

    @Transactional
    @Override
    public void updateSupplySyncStatusToCompleted(Long supplyId) {
        supplyCrudRepositoryMySql.updateSupplySyncStatus(supplyId, SyncStatus.COMPLETED.getDescription());
    }

    @Transactional
    @Override
    public void updateSupplySyncStatusToRejected(Long supplyId) {
        supplyCrudRepositoryMySql.updateSupplySyncStatus(supplyId, SyncStatus.FAILED.getDescription());
    }

    @Override
    public Optional<Supply> findSupplyById(Long supplyId) {
        Optional<SupplyEntity> supplyEntityOpt = supplyCrudRepositoryMySql.findById(supplyId);
        if(supplyEntityOpt.isEmpty()){
            return Optional.empty();
        }

        Supply supply = SupplyMapper.entityToDomain(supplyEntityOpt.get());
        return Optional.of(supply);
    }


}
