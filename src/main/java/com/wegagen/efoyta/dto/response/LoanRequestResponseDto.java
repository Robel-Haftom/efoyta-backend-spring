package com.wegagen.efoyta.dto.response;

public record LoanRequestResponseDto(
     String loanRequestId,
     String remark,
     boolean haveYouVisitedTheBusinessAddress,
     boolean isBusinessAddressComfortableForFollowUp,
     String requestStatus,
     Integer requestingBranchCode,
     String requestingBranchSenderName,
     SimpleLoanProductResponseDto loanProduct,
     SimpleCustomerResponseDto customer,
     String createdAt,
     String updatedAt
){}
