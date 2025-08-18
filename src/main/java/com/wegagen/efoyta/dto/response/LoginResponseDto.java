package com.wegagen.efoyta.dto.response;

public record LoginResponseDto(
         String fullName,
         String employeeName,
         String accountType,
         String branchCode,
         String workUnit,
         String clusterId,
         String positionId,
         String workstationId,
         String position,
         String clusterName
) {}
