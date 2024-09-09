package com.emazon.transaction.infraestructure.rest;


import com.emazon.transaction.infraestructure.rest.constants.HttpStatusCodes;
import com.emazon.transaction.infraestructure.rest.constants.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShopController {


    @Operation(
            summary = SwaggerConstants.BUY_SUMMARY,
            description = SwaggerConstants.BUY_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_OK, description = SwaggerConstants.BUY_API_RESPONSES_200_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_BAD_REQUEST, description = SwaggerConstants.BUY_API_RESPONSES_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = HttpStatusCodes.HTTP_FORBIDDEN, description = SwaggerConstants.BUY_API_RESPONSES_403_DESCRIPTION, content = @Content)
    })
    @PostMapping
    public ResponseEntity<String> buy() {
        return new ResponseEntity<>(SwaggerConstants.BUY_SUCCESS_MESSAGE, HttpStatus.OK);
    }


}

