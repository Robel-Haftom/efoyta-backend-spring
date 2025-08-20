package com.wegagen.efoyta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "Username")
    private String username;
    private String role;

    private String hash;
    private String salt;

    @Column(name = "Fullname")
    private String fullName;

    @Column(name = "EmployeeName")
    private String employeeName;

    @Column(name = "Account_type")
    private String accountType;

    @Column(name = "BRANCH_CODE")
    private String branchCode;

    @Column(name = "workunit")
    private String workUnit;

    @Column(name = "ID")
    private Long employeeId;

    @Column(name = "Cluster_id")
    private String clusterId;

    @Column(name = "position_id")
    private String positionId;

    @Column(name = "workstation_id")
    private String workstationId;

    private String position;

    @Column(name = "Cluster_Name")
    private String clusterName;

    @Column(name = "EmploymentStatus")
    private String employmentStatus;
}
