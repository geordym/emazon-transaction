package com.emazon.transaction.domain.ports.out;

import com.emazon.transaction.domain.model.Supply;

import java.util.List;
import java.util.Optional;

public interface SupplyPersistencePort {

    Supply saveSupply(Supply supply);

    List<Supply> getPendingSupplies(String status);

    void updateSupplyStatusToReceived(Long supplyId);
    void updateSupplyStatusToRejected(Long supplyId);

    Optional<Supply> findSupplyById(Long supplyId);

}
