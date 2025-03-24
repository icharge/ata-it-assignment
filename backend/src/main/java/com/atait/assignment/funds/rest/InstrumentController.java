package com.atait.assignment.funds.rest;

import com.atait.assignment.funds.api.InstrumentApi;
import com.atait.assignment.funds.dto.InstrumentResponse;
import com.atait.assignment.funds.dto.InstrumentSearchCriteria;
import com.atait.assignment.funds.entity.InstrumentType;
import com.atait.assignment.funds.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstrumentController implements InstrumentApi {

    private final InstrumentService service;

    @Override
    public List<InstrumentResponse> getAll() {
        return service.getAll();
    }

    @Override
    public List<InstrumentResponse> getByType(InstrumentType type) {
        return service.getByType(type);
    }

    @Override
    public Page<InstrumentResponse> searchInstruments(InstrumentSearchCriteria criteria, Pageable pageable) {
        return service.searchInstruments(criteria, pageable);
    }
}
