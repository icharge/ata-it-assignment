package com.atait.assignment.funds.service;

import com.atait.assignment.funds.dto.InstrumentResponse;
import com.atait.assignment.funds.dto.InstrumentSearchCriteria;
import com.atait.assignment.funds.entity.InstrumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InstrumentService {
    List<InstrumentResponse> getAll();

    List<InstrumentResponse> getByType(InstrumentType type);

    Page<InstrumentResponse> searchInstruments(
            InstrumentSearchCriteria criteria,
            Pageable pageable
    );
}
