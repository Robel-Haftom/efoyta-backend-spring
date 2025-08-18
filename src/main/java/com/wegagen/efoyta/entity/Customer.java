package com.wegagen.efoyta.entity;

import com.wegagen.efoyta.commons.enums.Gender;
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
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_customer",
                        columnNames = {"id"}
                )
        }
)
public class Customer {

    @SequenceGenerator(
            name = "customer_sequence_generator",
            sequenceName= "customer_sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence_generator"
    )
    @Id
    private Long id;

    @Column(name = "customer_id", unique = true, updatable = false, nullable = false)
    private UUID customerId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "account_branch", nullable = false)
    private String accountBranch;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToMany(mappedBy = "customer")
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
        this.customerId = UUID.randomUUID();
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
