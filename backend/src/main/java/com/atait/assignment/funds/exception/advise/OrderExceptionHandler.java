package com.atait.assignment.funds.exception.advise;

import com.atait.assignment.funds.dto.OrderErrorResponse;
import com.atait.assignment.funds.exception.OrderPlacingException;
import com.atait.assignment.funds.exception.OrderValidationException;
import com.atait.assignment.funds.rest.OrderController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(assignableTypes = OrderController.class)
public class OrderExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OrderErrorResponse> handleDtoValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(OrderErrorResponse.failure(errors));
    }

    @ExceptionHandler(OrderPlacingException.class)
    public ResponseEntity<OrderErrorResponse> handleGeneric(OrderPlacingException ex) {
        HttpStatus status = ex.getClass().getAnnotation(ResponseStatus.class).value();
        if (ex instanceof OrderValidationException validationEx) {
            return ResponseEntity.status(status).body(OrderErrorResponse.failure(validationEx.getErrors()));
        }

        return ResponseEntity.status(status).body(OrderErrorResponse.failure(ex.getMessage()));
    }
}
