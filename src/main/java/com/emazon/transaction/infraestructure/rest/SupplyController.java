package com.emazon.transaction.infraestructure.rest;

import com.emazon.transaction.application.dto.rest.CreateSupplyRequestDto;
import com.emazon.transaction.application.dto.rest.GenericResponseDto;
import com.emazon.transaction.application.services.interfaces.ISupplyService;
import com.emazon.transaction.infraestructure.rest.constants.HttpStatusCodes;
import com.emazon.transaction.infraestructure.rest.constants.SwaggerConstants;
import com.emazon.transaction.infraestructure.util.ValidateSupplyId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final ValidateSupplyId supplyIdValidator;


    @Operation(
            summary = SwaggerConstants.CREATE_SUPPLY_SUMMARY,
            description = SwaggerConstants.CREATE_SUPPLY_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_CREATED, description = SwaggerConstants.CREATE_SUPPLY_API_RESPONSES_201_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_BAD_REQUEST, description = SwaggerConstants.CREATE_SUPPLY_API_RESPONSES_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_FORBIDDEN, description = SwaggerConstants.CREATE_SUPPLY_API_RESPONSES_403_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public void createSupply(@RequestBody @Valid CreateSupplyRequestDto createSupplyRequestDto){
        supplyService.createSupply(createSupplyRequestDto);
    }

    @Operation(
            summary = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_SUMMARY,
            description = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_OK, description = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_API_RESPONSES_200_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_NOT_FOUND, description = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_API_RESPONSES_404_DESCRIPTION, content = @Content)
    })
    @PutMapping("/confirm/{supplyId}")
    public ResponseEntity<GenericResponseDto> confirmSupplyReceived(@PathVariable String supplyId) {
        supplyIdValidator.validateSupplyId(supplyId);
        supplyService.confirmReceiptOfSupply(Long.parseLong(supplyId));
        GenericResponseDto genericResponseDto = new GenericResponseDto(SwaggerConstants.CONFIRM_RECEIVED_MESSAGE);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }


    @Operation(
            summary = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_SUMMARY,
            description = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_OK, description = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_API_RESPONSES_200_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_NOT_FOUND, description = SwaggerConstants.CONFIRM_SUPPLY_RECEIPT_API_RESPONSES_404_DESCRIPTION, content = @Content)
    })
    @PutMapping("/cancel/{supplyId}")
    public ResponseEntity<GenericResponseDto> cancelSupply(@PathVariable String supplyId) {
        supplyIdValidator.validateSupplyId(supplyId);
        supplyService.cancelReceiptOfSupply(Long.parseLong(supplyId));
        GenericResponseDto genericResponseDto = new GenericResponseDto(SwaggerConstants.CONFIRM_REJECTED_MESSAGE);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }


}
