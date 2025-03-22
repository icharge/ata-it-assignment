package com.atait.assignment.funds.repository;

import com.atait.assignment.funds.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {
}
