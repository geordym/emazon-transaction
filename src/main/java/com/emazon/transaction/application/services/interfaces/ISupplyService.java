package com.emazon.transaction.application.services.interfaces;

import com.emazon.transaction.application.dto.rest.CreateSupplyRequestDto;
import com.emazon.transaction.domain.model.Supply;

import java.util.Optional;

public interface ISupplyService {

    void createSupply(CreateSupplyRequestDto createSupplyRequestDto);
    void confirmReceiptOfSupply(Long supplyId);
    void cancelReceiptOfSupply(Long supplyId);

    Supply getUpcomingSupplyForArticle(Long articleId);

}
