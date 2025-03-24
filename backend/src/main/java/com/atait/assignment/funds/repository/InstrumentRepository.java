package com.atait.assignment.funds.repository;

import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface InstrumentRepository extends JpaRepository<Instrument, UUID>, JpaSpecificationExecutor<Instrument> {

    List<Instrument> findByType(InstrumentType type);

}
