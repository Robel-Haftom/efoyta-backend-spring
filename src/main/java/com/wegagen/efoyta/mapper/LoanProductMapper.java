package com.wegagen.efoyta.mapper;

import com.wegagen.efoyta.dto.request.LoanProductRequestDto;
import com.wegagen.efoyta.dto.response.LoanProductResponseDto;
import com.wegagen.efoyta.dto.response.LoanRequestResponseDto;
import com.wegagen.efoyta.dto.response.SimpleLoanProductResponseDto;
import com.wegagen.efoyta.entity.LoanProduct;

public class LoanProductMapper {

    public static LoanProduct mapToLoanProductEntity(LoanProductRequestDto requestDto){
        return LoanProduct.builder()
                .loanProductName(requestDto.getLoanProductName())
                .build();
    }

    public static LoanProductResponseDto mapToLoanProductResponse(LoanProduct loanProduct){
        return new LoanProductResponseDto(
                loanProduct.getLoanProductId().toString(),
                loanProduct.getLoanProductName(),
                loanProduct.getLoanRequests().stream().map(LoanRequestMapper::mapToSimpleLoanRequestResponseDto).toList()
        );
    }

    public static SimpleLoanProductResponseDto mapToSimpleLoanProductResponse(LoanProduct loanProduct){
        return new SimpleLoanProductResponseDto(
                loanProduct.getLoanProductId().toString(),
                loanProduct.getLoanProductName()
        );
    }
}
