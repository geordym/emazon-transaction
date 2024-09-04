package com.emazon.transaction.domain.ports.in;

import com.emazon.transaction.domain.model.Supply;

import java.util.List;

public interface SupplyUseCases {

    List<Supply> listSupply();
    void createSupply(Supply supply);

}
