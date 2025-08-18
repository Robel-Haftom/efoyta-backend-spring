package com.wegagen.efoyta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "loan_products",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_loan_product",
                        columnNames = {"id"}
                )
        }
)
public class LoanProduct {

    @SequenceGenerator(
            name = "loan_product_sequence_generator",
            sequenceName= "loan_product_sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_product_sequence_generator"
    )
    @Id
    private Long id;

    @Column(name = "loan_product_id", unique = true, updatable = false, nullable = false)
    private UUID loanProductId;

    @Column(name = "loan_product_name", nullable = false, unique = true)
    private String loanProductName;

    @OneToMany(mappedBy = "loanProduct")
    @Builder.Default
    private List<LoanRequest> loanRequests = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.loanProductId = UUID.randomUUID();
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
