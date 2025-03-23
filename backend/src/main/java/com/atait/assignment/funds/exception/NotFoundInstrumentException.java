package com.atait.assignment.funds.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundInstrumentException extends OrderPlacingException {
    private final UUID instrumentId;

    public NotFoundInstrumentException(UUID instrumentId) {
        super("Instrument not found: %s".formatted(instrumentId));
        this.instrumentId = instrumentId;
    }
}
