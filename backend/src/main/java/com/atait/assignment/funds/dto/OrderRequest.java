package com.atait.assignment.funds.dto;

import com.atait.assignment.funds.entity.InstrumentType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderRequest {

    private InstrumentType instrumentType;
    private UUID instrumentId;
    private BigDecimal dealtPrice;
    private BigDecimal amount;

}
