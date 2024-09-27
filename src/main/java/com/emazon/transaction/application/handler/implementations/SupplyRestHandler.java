package com.emazon.transaction.application.handler.implementations;

import com.emazon.transaction.application.dto.rest.response.SupplyResponseDto;
import com.emazon.transaction.application.handler.ISupplyRestHandler;
import com.emazon.transaction.application.services.interfaces.ISupplyService;
import com.emazon.transaction.application.services.mapper.SupplyMapper;
import com.emazon.transaction.domain.model.Supply;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyRestHandler implements ISupplyRestHandler {
    private final ISupplyService supplyService;

    @Override
    public SupplyResponseDto getUpcomingSupplyForArticle(Long articleId) {
        Supply supply = supplyService.getUpcomingSupplyForArticle(articleId);
        SupplyResponseDto supplyResponseDto = SupplyMapper.domainToDto(supply);
        return supplyResponseDto;
    }
}
