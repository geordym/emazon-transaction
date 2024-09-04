package com.emazon.transaction.infraestructure.adapter;

import com.emazon.transaction.application.services.mapper.SupplyMapper;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.out.SupplyPersistencePort;
import com.emazon.transaction.infraestructure.entities.SupplyEntity;
import com.emazon.transaction.infraestructure.repository.SupplyCrudRepositoryMySql;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyPersistenceMysqlAdapter implements SupplyPersistencePort {
    private final SupplyCrudRepositoryMySql supplyCrudRepositoryMySql;
    @Override
    public void saveSupply(Supply supply) {
        SupplyEntity supplyEntity = SupplyMapper.domainToEntity(supply);
        supplyCrudRepositoryMySql.save(supplyEntity);
    }


}
