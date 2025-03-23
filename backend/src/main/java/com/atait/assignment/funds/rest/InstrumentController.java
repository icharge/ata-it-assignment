package com.atait.assignment.funds.rest;

import com.atait.assignment.funds.api.InstrumentApi;
import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import com.atait.assignment.funds.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstrumentController implements InstrumentApi {

    private final InstrumentService service;

    @Override
    public List<Instrument> getAll() {
        return service.getAll();
    }

    @Override
    public List<Instrument> getByType(InstrumentType type) {
        return service.getByType(type);
    }
}
