package com.wegagen.efoyta.dto.response;

public record SimpleCustomerResponseDto (
     String customerId,
     String fullName,
     String accountNumber,
     String accountBranch,
     String phoneNumber,
     String gender
){}
