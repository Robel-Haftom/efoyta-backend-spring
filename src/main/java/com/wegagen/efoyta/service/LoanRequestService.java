package com.wegagen.efoyta.service;

import com.wegagen.efoyta.dto.request.LoanRequestRequestDto;
import com.wegagen.efoyta.dto.response.LoanRequestResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface LoanRequestService {
    Page<LoanRequestResponseDto> getAllLoanRequests(int pageNumber, int pageSize, String sortBy, String sortDir);

    LoanRequestResponseDto getLoanRequestById(UUID loanRequestId);

    LoanRequestResponseDto createLoanRequest(LoanRequestRequestDto requestDto);

    LoanRequestResponseDto updateLoanRequest(UUID loanRequestId, LoanRequestRequestDto requestDto);

    Page<LoanRequestResponseDto> getLoanRequestByUser(String userFullName, int pageNumber, int pageSize, String sortBy, String sortDir);
}
