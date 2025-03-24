package com.atait.assignment.funds.dto;

import com.atait.assignment.funds.entity.InstrumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Instrument info")
public class InstrumentResponse {

    @Schema(example = "a1b2c3d4-0001-0000-0000-000000000001")
    private UUID id;

    @Schema(example = "Real Estate Fund")
    private String name;

    @Schema(example = "MUTUAL_FUND")
    private InstrumentType type;

    private BigDecimal interestRate;

    private String accountNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDate maturityDate;
}
