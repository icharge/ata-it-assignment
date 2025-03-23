package com.atait.assignment.funds.api;

import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/instruments")
@Tag(name = "Instruments", description = "Instrument info and fund lookup APIs")
public interface InstrumentApi {

    @GetMapping
    @Operation(summary = "Get all instruments", description = "Returns all Mutual Funds, Index Funds, and Fixed Income instruments")
    List<Instrument> getAll();

    @GetMapping("/type/{type}")
    @Operation(summary = "Get instruments by type", description = "Filter instruments by type (MUTUAL_FUND, INDEX_FUND, FIXED_INCOME)")
    List<Instrument> getByType(@PathVariable InstrumentType type);

}
