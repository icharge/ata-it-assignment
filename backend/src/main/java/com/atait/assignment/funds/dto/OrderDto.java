package com.atait.assignment.funds.dto;

import com.atait.assignment.funds.entity.InstrumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Detailed order response")
public class OrderDto {

    private UUID id;

    private UUID instrumentId;

    private String instrumentName;

    private InstrumentType instrumentType;

    private BigDecimal dealtPrice;

    private BigDecimal amount;

    private LocalDateTime timestamp;

}
