package com.wegagen.efoyta.dto.response;

public record LoginResponseDto(
         String userName,
         String fullName,
         String employeeName,
         String role,
         String accountType,
         String branchCode,
         String workUnit,
         String clusterId,
         String positionId,
         String workstationId,
         String position,
         String clusterName,
         String token
) {}
