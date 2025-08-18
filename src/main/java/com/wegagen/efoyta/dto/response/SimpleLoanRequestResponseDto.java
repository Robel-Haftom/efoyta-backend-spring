package com.wegagen.efoyta.dto.response;

public record SimpleLoanRequestResponseDto(
     String loanRequestId,
     String remark,
     String requestStatus,
     Integer requestingBranchCode,
     String requestingBranchSenderName,
     String createdAt,
     String updatedAt
){}
