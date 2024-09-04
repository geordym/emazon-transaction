package com.emazon.transaction.domain.ports.out;

import com.emazon.transaction.domain.model.Supply;

public interface SupplyPersistencePort {

    void saveSupply(Supply supply);

}
