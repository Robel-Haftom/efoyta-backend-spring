package com.wegagen.efoyta.mapper;

import com.wegagen.efoyta.dto.request.LoanRequestRequestDto;
import com.wegagen.efoyta.dto.response.LoanRequestResponseDto;
import com.wegagen.efoyta.dto.response.SimpleCustomerResponseDto;
import com.wegagen.efoyta.dto.response.SimpleLoanProductResponseDto;
import com.wegagen.efoyta.dto.response.SimpleLoanRequestResponseDto;
import com.wegagen.efoyta.entity.LoanRequest;

public class LoanRequestMapper {

    public static LoanRequest mapToLoanRequestEntity(LoanRequestRequestDto requestDto){
        return LoanRequest.builder()
                .remark(requestDto.getRemark())
                .haveYouVisitedTheBusinessAddress(requestDto.getHaveYouVisitedTheBusinessAddress())
                .isBusinessAddressComfortableForFollowUp(requestDto.getIsBusinessAddressComfortableForFollowUp())
                .requestingBranchCode(requestDto.getRequestingBranchCode())
                .requestingBranchSenderName(requestDto.getRequestingBranchSenderName())
                .build();

        //todo remove the sender name and branch code after implementing the security
    }

    public static LoanRequestResponseDto mapToLoanRequestResponseDto(LoanRequest loanRequest){
        return new LoanRequestResponseDto(
                loanRequest.getLoanRequestId().toString(),
                loanRequest.getRemark(),
                loanRequest.getHaveYouVisitedTheBusinessAddress(),
                loanRequest.getIsBusinessAddressComfortableForFollowUp(),
                loanRequest.getRequestStatus().toString(),
                loanRequest.getRequestingBranchCode(),
                loanRequest.getRequestingBranchSenderName(),
                LoanProductMapper.mapToSimpleLoanProductResponse(loanRequest.getLoanProduct()),
                CustomerMapper.mapToSimpleCustomerResponseDto(loanRequest.getCustomer()),
                loanRequest.getCreatedAt().toString(),
                loanRequest.getUpdatedAt().toString()
                );
    }

    public static SimpleLoanRequestResponseDto mapToSimpleLoanRequestResponseDto(LoanRequest loanRequest){
        return new SimpleLoanRequestResponseDto(
                loanRequest.getLoanRequestId().toString(),
                loanRequest.getRemark(),
                loanRequest.getRequestStatus().toString(),
                loanRequest.getRequestingBranchCode(),
                loanRequest.getRequestingBranchSenderName(),
                loanRequest.getCreatedAt().toString(),
                loanRequest.getUpdatedAt().toString()
                );
    }
}
