package com.wegagen.efoyta.repository;

import com.wegagen.efoyta.entity.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(UUID customerId);

    Optional<Customer> findByAccountNumber(String accountNumber);
}
