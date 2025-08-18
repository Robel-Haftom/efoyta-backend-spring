package com.wegagen.efoyta.controller;

import com.wegagen.efoyta.dto.request.LoanProductRequestDto;
import com.wegagen.efoyta.dto.response.LoanProductResponseDto;
import com.wegagen.efoyta.dto.response.SimpleLoanProductResponseDto;
import com.wegagen.efoyta.service.LoanProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loanProducts")
@RequiredArgsConstructor
public class LoanProductController {

    private final LoanProductService loanProductService;

    @GetMapping()
    public ResponseEntity<List<SimpleLoanProductResponseDto>> getAllSimpleLoanProducts(){
        return ResponseEntity.ok(loanProductService.getAllSimpleLoanProducts());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LoanProductResponseDto>> getAllLoanProducts(){
        return ResponseEntity.ok(loanProductService.getAllLoanProducts());
    }

    @PostMapping()
    public ResponseEntity<SimpleLoanProductResponseDto> createLoanProduct(@Valid @RequestBody LoanProductRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanProductService.createLoanProduct(requestDto));
    }

    @PutMapping("/{loanProductId}")
    public ResponseEntity<SimpleLoanProductResponseDto> updateLoanProduct(@PathVariable UUID loanProductId,
                                                                          @Valid @RequestBody LoanProductRequestDto requestDto){
        return ResponseEntity.ok(loanProductService.updateLoanProduct(loanProductId, requestDto));
    }

    @DeleteMapping("/{loanProductId}")
    public ResponseEntity<String> deleteLoanProduct(@PathVariable UUID loanProductId){
        loanProductService.deleteLoanProduct(loanProductId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
