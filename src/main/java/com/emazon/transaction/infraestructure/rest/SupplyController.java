package com.emazon.transaction.infraestructure.rest;

import com.emazon.transaction.application.dto.rest.CreateSupplyRequestDto;
import com.emazon.transaction.application.services.interfaces.ISupplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SupplyController {

    private final ISupplyService supplyService;
    @PostMapping
    public void createSupply(@RequestBody @Valid CreateSupplyRequestDto createSupplyRequestDto){
        supplyService.createSupply(createSupplyRequestDto);
    }

}
