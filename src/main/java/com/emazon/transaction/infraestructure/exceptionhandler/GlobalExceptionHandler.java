package com.emazon.transaction.infraestructure.exceptionhandler;


import com.emazon.transaction.application.dto.rest.security.ErrorGenericResponseDto;
import com.emazon.transaction.domain.exception.util.ArticleNotFoundException;
import com.emazon.transaction.domain.exception.util.SupplyNotFoundException;
import com.emazon.transaction.infraestructure.exception.InvalidSupplyIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidSupplyIdException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleInvalidSupplyIdException(InvalidSupplyIdException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }
    @ExceptionHandler(SupplyNotFoundException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleSupplyNotFoundException(SupplyNotFoundException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ErrorGenericResponseDto> handleArticleNotFoundException(ArticleNotFoundException ex){
        ErrorGenericResponseDto errorGenericResponseDto = new ErrorGenericResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamps());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorGenericResponseDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

}
