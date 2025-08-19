package com.wegagen.efoyta.entity;

import com.wegagen.efoyta.commons.enums.RequestStatus;
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
        name = "loan_requests",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_loan_request",
                        columnNames = {"id"}
                )
        }
)
public class LoanRequest {
    @SequenceGenerator(
            name = "loan_request_sequence_generator",
            sequenceName= "loan_request_sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_request_sequence_generator"
    )
    @Id
    private Long id;

    @Column(name = "loan_request_id", unique = true, updatable = false, nullable = false)
    private UUID loanRequestId;

    @Column(name = "business_line", nullable = false)
    private String businessLine;

    @Column(name = "business_address_visited", nullable = false)
    private Boolean haveYouVisitedTheBusinessAddress;

    @Column(name = "is_address_comfortable", nullable = false)
    private Boolean isBusinessAddressComfortableForFollowUp;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @Column(name = "requesting_branch_name", nullable = false)
    private String requestingBranchName;

    @Column(name = "sender_name", nullable = false)
    private String requestingBranchSenderName;

    @Column(name = "request_handler")
    private String requestHandledBy;

    @ManyToOne
    @JoinColumn(name = "loan_product_id")
    private LoanProduct loanProduct;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "loanRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Remark> remarks = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.loanRequestId = UUID.randomUUID();
        this.requestStatus = RequestStatus.PENDING;
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
