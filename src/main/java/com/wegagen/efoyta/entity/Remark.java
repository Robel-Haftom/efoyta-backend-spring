package com.wegagen.efoyta.entity;

import com.wegagen.efoyta.commons.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "remarks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_remark_request",
                        columnNames = {"id"}
                )
        }
)
public class Remark {
    @SequenceGenerator(
            name = "remark_sequence_generator",
            sequenceName= "remark_sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "remark_sequence_generator"
    )
    @Id
    private Long id;

    @Column(name = "remark_id", unique = true, updatable = false, nullable = false)
    private UUID remarkId;

    @Column(name = "remark_text", columnDefinition = "TEXT")
    private String remarkText;

    @Column(name = "author", nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.remarkId = UUID.randomUUID();
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
