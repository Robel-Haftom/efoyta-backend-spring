package com.wegagen.efoyta.service;

import com.wegagen.efoyta.dto.request.LoanRequestRequestDto;
import com.wegagen.efoyta.dto.response.LoanRequestResponseDto;

import java.util.List;
import java.util.UUID;

public interface LoanRequestService {
    List<LoanRequestResponseDto> getAllLoanRequests();

    LoanRequestResponseDto getLoanRequestById(UUID loanRequestId);

    LoanRequestResponseDto createLoanRequest(LoanRequestRequestDto requestDto);

    LoanRequestResponseDto updateLoanRequest(UUID loanRequestId, LoanRequestRequestDto requestDto);
}
