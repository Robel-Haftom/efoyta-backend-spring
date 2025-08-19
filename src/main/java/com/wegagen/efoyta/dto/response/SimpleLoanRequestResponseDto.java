package com.wegagen.efoyta.dto.response;

import java.util.List;

public record SimpleLoanRequestResponseDto(
     String loanRequestId,
     List<RemarkResponseDto> remarkResponseDto,
     String businessLine,
     String requestStatus,
     String requestingBranchName,
     String requestingBranchSenderName,
     String createdAt,
     String updatedAt
){}
