package com.emazon.transaction.application.services.implementations;

import com.emazon.transaction.application.dto.rest.CreateSupplyRequestDto;
import com.emazon.transaction.application.services.interfaces.ISupplyService;
import com.emazon.transaction.application.services.mapper.SupplyMapper;
import com.emazon.transaction.domain.model.Supply;
import com.emazon.transaction.domain.ports.in.SupplyUseCases;
import com.emazon.transaction.infraestructure.configuration.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@RequiredArgsConstructor
public class SupplyServiceImpl implements ISupplyService {

    private final SupplyUseCases supplyUseCases;
    private final String EXCEPT_AUTH_MESSAGE = "You need to be autenticated";
    @Override
    public void createSupply(CreateSupplyRequestDto createSupplyRequestDto) {
        Supply supplyToSave = SupplyMapper.dtoToDomain(createSupplyRequestDto);
        supplyUseCases.createSupply(supplyToSave, createSupplyRequestDto.getIsReceived());
    }

    @Override
    public void confirmReceiptOfSupply(Long supplyId) {
        supplyUseCases.processReceivedSupply(supplyId);
    }

    @Override
    public void cancelReceiptOfSupply(Long supplyId) {
        supplyUseCases.processRejectedSupply(supplyId);
    }

    @Override
    public Supply getUpcomingSupplyForArticle(Long articleId) {
        return supplyUseCases.getUpcomingSupplyForArticle(articleId);
    }


}
