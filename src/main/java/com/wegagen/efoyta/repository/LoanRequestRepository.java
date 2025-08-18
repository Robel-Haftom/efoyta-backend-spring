package com.wegagen.efoyta.repository;

import com.wegagen.efoyta.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
    Optional<LoanRequest> findByLoanRequestId(UUID loanRequestId);
}
