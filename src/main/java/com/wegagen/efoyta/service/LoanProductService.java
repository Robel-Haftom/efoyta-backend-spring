package com.wegagen.efoyta.service;

import com.wegagen.efoyta.dto.request.LoanProductRequestDto;
import com.wegagen.efoyta.dto.response.LoanProductResponseDto;
import com.wegagen.efoyta.dto.response.SimpleLoanProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface LoanProductService {
    List<LoanProductResponseDto> getAllLoanProducts();
    List<SimpleLoanProductResponseDto> getAllSimpleLoanProducts();

    SimpleLoanProductResponseDto createLoanProduct(LoanProductRequestDto requestDto);

    SimpleLoanProductResponseDto updateLoanProduct(UUID loanProductId, LoanProductRequestDto requestDto);

    void deleteLoanProduct(UUID loanProductId);
}
