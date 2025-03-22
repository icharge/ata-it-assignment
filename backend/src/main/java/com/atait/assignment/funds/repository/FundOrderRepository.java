package com.atait.assignment.funds.repository;

import com.atait.assignment.funds.entity.FundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FundOrderRepository extends JpaRepository<FundOrder, UUID> {
}
