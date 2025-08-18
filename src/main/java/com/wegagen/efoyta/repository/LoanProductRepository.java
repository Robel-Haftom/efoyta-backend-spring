package com.wegagen.efoyta.repository;

import com.wegagen.efoyta.entity.LoanProduct;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {
    Optional<LoanProduct> findByLoanProductName(String loanProductName);

    Optional<LoanProduct> findByLoanProductId(UUID loanProductId);
}
