package com.emazon.transaction.application.dto.rest.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorGenericResponseDto {
    private String error;
    private String message;
    private String timestamp;

}