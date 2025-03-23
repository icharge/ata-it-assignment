package com.atait.assignment.funds.repository;

import com.atait.assignment.funds.entity.Instrument;
import com.atait.assignment.funds.entity.InstrumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {

    List<Instrument> findByType(InstrumentType type);

}
