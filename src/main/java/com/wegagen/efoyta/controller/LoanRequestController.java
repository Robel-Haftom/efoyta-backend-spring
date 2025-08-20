package com.wegagen.efoyta.controller;

import com.wegagen.efoyta.dto.request.LoanRequestRequestDto;
import com.wegagen.efoyta.dto.response.LoanRequestResponseDto;
import com.wegagen.efoyta.service.LoanRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loanRequests")
@RequiredArgsConstructor
public class LoanRequestController {

    private final LoanRequestService loanRequestService;

    @GetMapping()
    public ResponseEntity<Page<LoanRequestResponseDto>> getAllLoanRequests(@RequestParam(defaultValue = "0") int pageNo,
                                                                           @RequestParam(defaultValue = "10") int pageSize,
                                                                           @RequestParam(defaultValue = "createdAt") String sortBy,
                                                                           @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(loanRequestService.getAllLoanRequests(pageNo, pageSize, sortBy, sortDir));
    }

    @GetMapping("/{loanRequestId}")
    public ResponseEntity<LoanRequestResponseDto> getLoanRequestById(@PathVariable UUID loanRequestId){
        return ResponseEntity.ok(loanRequestService.getLoanRequestById(loanRequestId));
    }

    @GetMapping("/user/{userFullName}")
    public ResponseEntity<Page<LoanRequestResponseDto>> getLoanRequestByUser(@PathVariable String userFullName,
                                                                                @RequestParam(defaultValue = "0") int pageNo,
                                                                                @RequestParam(defaultValue = "10") int pageSize,
                                                                                @RequestParam(defaultValue = "createdAt") String sortBy,
                                                                                @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(loanRequestService.getLoanRequestByUser(userFullName, pageNo, pageSize, sortBy, sortDir));
    }

    @PostMapping()
    public ResponseEntity<LoanRequestResponseDto> createLoanRequest(@RequestBody LoanRequestRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanRequestService.createLoanRequest(requestDto));
    }

    @PutMapping("/{loanRequestId}")
    public ResponseEntity<LoanRequestResponseDto> updateLoanRequest(@PathVariable UUID loanRequestId,
                                                                    @RequestBody LoanRequestRequestDto requestDto){
        return ResponseEntity.ok(loanRequestService.updateLoanRequest(loanRequestId, requestDto));
    }

}
