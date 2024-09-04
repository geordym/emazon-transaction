package com.emazon.transaction.application.services.interfaces;

import com.emazon.transaction.application.dto.rest.CreateSupplyRequestDto;

public interface ISupplyService {

    void createSupply(CreateSupplyRequestDto createSupplyRequestDto);
}
