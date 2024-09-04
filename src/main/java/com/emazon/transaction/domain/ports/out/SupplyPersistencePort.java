package com.emazon.transaction.domain.ports.out;

import com.emazon.transaction.domain.model.Supply;

import java.util.List;

public interface SupplyPersistencePort {

    Supply saveSupply(Supply supply);

    List<Supply> getPendingSupplies();

    void updateSupplyStatusToReceived(Long supplyId);

}
