package com.atait.assignment.funds.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstrumentValidationRuleAbsentException extends OrderPlacingException {
    private final UUID instrumentId;

    public InstrumentValidationRuleAbsentException(UUID instrumentId) {
        super("Instrument validation rule absent: %s".formatted(instrumentId));
        this.instrumentId = instrumentId;
    }
}
