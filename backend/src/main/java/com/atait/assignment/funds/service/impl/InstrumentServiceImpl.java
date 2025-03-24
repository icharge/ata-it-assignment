package com.atait.assignment.funds.service.impl;

import com.atait.assignment.funds.dto.InstrumentResponse;
import com.atait.assignment.funds.dto.InstrumentSearchCriteria;
import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import com.atait.assignment.funds.mapper.InstrumentMapper;
import com.atait.assignment.funds.repository.InstrumentRepository;
import com.atait.assignment.funds.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository repository;
    private final InstrumentMapper mapper;

    @Override
    public List<InstrumentResponse> getAll() {
        List<Instrument> instrumentList = repository.findAll();

        return mapper.toDtoList(instrumentList);
    }

    @Override
    public List<InstrumentResponse> getByType(InstrumentType type) {
        List<Instrument> instrumentList = repository.findByType(type);

        return mapper.toDtoList(instrumentList);
    }

    @Override
    public Page<InstrumentResponse> searchInstruments(InstrumentSearchCriteria criteria, Pageable pageable) {
        Specification<Instrument> spec = Specification.where(null);

        if (criteria.getInstrumentType() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("instrumentType"), criteria.getInstrumentType()));
        }
        if (criteria.getName() != null && !criteria.getName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
        }
        if (criteria.getAccountNumber() != null && !criteria.getAccountNumber().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("accountNumber"), criteria.getAccountNumber()));
        }
        if (criteria.getMonths() != null) {
            if (criteria.getInstrumentType() != null && criteria.getInstrumentType() == InstrumentType.FIXED_INCOME) {
                // For Fixed Income, filter by maturityDate within next 'months'
                LocalDate cutoff = LocalDate.now().plusMonths(criteria.getMonths());
                spec = spec.and((root, query, cb) ->
                        cb.lessThanOrEqualTo(root.get("maturityDate"), cutoff));
            } else {
                // For other types, filter by createdAt within last 'months'
                LocalDateTime cutoff = LocalDateTime.now().minusMonths(criteria.getMonths());
                spec = spec.and((root, query, cb) ->
                        cb.greaterThan(root.get("createdAt"), cutoff));
            }
        }
        if (criteria.getInterestRate() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("interestRate"), criteria.getInterestRate()));
        }
        if (criteria.getMaturityDate() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("maturityDate"), criteria.getMaturityDate()));
        }
        if (criteria.getStatus() != null && !criteria.getStatus().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("status"), criteria.getStatus()));
        }

        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

}
