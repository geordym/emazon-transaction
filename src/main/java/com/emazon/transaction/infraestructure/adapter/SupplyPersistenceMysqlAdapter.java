package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.application.services.mapper.SupplyMapper;
import com.emazon.transaction.domain.enums.SupplyStatus;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import com.emazon.transaction.infraestructure.entities.SupplyEntity;
import com.emazon.transaction.infraestructure.repository.SupplyCrudRepositoryMySql;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public List<Supply> getPendingSupplies() {
        return null;
    }

    @Transactional
    @Override
    public void updateSupplyStatusToReceived(Long supplyId) {
        supplyCrudRepositoryMySql.updateSupplyStatus(supplyId, SupplyStatus.ACCEPTED.getDisplayName());
    }


}
