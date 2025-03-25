package com.atait.assignment.funds.api;

import com.atait.assignment.funds.dto.InstrumentResponse;
import com.atait.assignment.funds.dto.InstrumentSearchCriteria;
import com.atait.assignment.funds.entity.InstrumentType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/instruments")
@Tag(name = "Instruments", description = "Instrument info and fund lookup APIs")
public interface InstrumentApi {

    @GetMapping
    @Operation(summary = "Get all instruments", description = "Returns all Mutual Funds, Index Funds, and Fixed Income instruments")
    List<InstrumentResponse> getAll();

    @GetMapping("/type/{type}")
    @Operation(summary = "Get instruments by type", description = "Filter instruments by type (MUTUAL_FUND, INDEX_FUND, FIXED_INCOME)")
    List<InstrumentResponse> getByType(@PathVariable InstrumentType type);

    @GetMapping("/search")
    @Operation(summary = "Search instruments",
            description = "Search instruments based on filters such as type, name, account number, months, interest rate, maturity date, and status.")
    Page<InstrumentResponse> searchInstruments(
            @ParameterObject InstrumentSearchCriteria criteria,
            @ParameterObject Pageable pageable
    );

}
