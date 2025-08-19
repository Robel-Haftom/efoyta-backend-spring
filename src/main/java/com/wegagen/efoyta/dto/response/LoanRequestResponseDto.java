package com.wegagen.efoyta.dto.response;

import java.util.List;

public record LoanRequestResponseDto(
     String loanRequestId,
     List<RemarkResponseDto> remarkResponseDto,
     String businessLine,
     boolean haveYouVisitedTheBusinessAddress,
     boolean isBusinessAddressComfortableForFollowUp,
     String requestStatus,
     String requestingBranchName,
     String requestingBranchSenderName,
     SimpleLoanProductResponseDto loanProduct,
     SimpleCustomerResponseDto customer,
     String createdAt,
     String updatedAt
){}
