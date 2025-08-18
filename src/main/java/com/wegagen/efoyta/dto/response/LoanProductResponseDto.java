package com.wegagen.efoyta.dto.response;

import java.util.List;

public record LoanProductResponseDto (
     String loanProductId,
     String loanProductName,
     List<SimpleLoanRequestResponseDto> loanRequests){}

