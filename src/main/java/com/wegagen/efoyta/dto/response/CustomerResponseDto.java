package com.wegagen.efoyta.dto.response;

import java.util.List;

public record CustomerResponseDto(
     String customerId,
     String fullName,
     String accountNumber,
     String accountBranch,
     String phoneNumber,
     String gender,
     List<SimpleLoanRequestResponseDto> loanRequests
     ){}
