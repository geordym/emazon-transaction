package com.emazon.transaction.infraestructure.rest;

import com.emazon.transaction.application.dto.rest.CreateSupplyRequestDto;
import com.emazon.transaction.application.dto.rest.GenericResponseDto;
import com.emazon.transaction.application.services.interfaces.ISupplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.emazon.transaction.infraestructure.rest.constants.ResponseConstantes.CONFIRM_RECEIVED_MESSAGE;

@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SupplyController {

    private final ISupplyService supplyService;
    @PostMapping
    public void createSupply(@RequestBody @Valid CreateSupplyRequestDto createSupplyRequestDto){
        supplyService.createSupply(createSupplyRequestDto);
    }


    @PutMapping("/confirm/{supplyId}")
    public ResponseEntity<GenericResponseDto> confirmSupplyReceived(@PathVariable Long supplyId){
        supplyService.confirmReceiptOfSupply(supplyId);
        GenericResponseDto genericResponseDto = new GenericResponseDto(CONFIRM_RECEIVED_MESSAGE);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }


}
