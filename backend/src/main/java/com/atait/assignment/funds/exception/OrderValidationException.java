package com.atait.assignment.funds.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderValidationException extends OrderPlacingException {
    private final List<String> errors;

    public OrderValidationException(List<String> errors) {
        super("Validation failed");
        this.errors = Collections.unmodifiableList(errors);
    }
}
