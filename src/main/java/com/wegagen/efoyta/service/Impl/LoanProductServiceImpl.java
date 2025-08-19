package com.wegagen.efoyta.service.Impl;

import com.wegagen.efoyta.dto.request.LoanProductRequestDto;
import com.wegagen.efoyta.dto.response.LoanProductResponseDto;
import com.wegagen.efoyta.dto.response.SimpleLoanProductResponseDto;
import com.wegagen.efoyta.entity.LoanProduct;
import com.wegagen.efoyta.exception.ResourceExistsException;
import com.wegagen.efoyta.exception.ResourceNotFoundException;
import com.wegagen.efoyta.mapper.LoanProductMapper;
import com.wegagen.efoyta.repository.LoanProductRepository;
import com.wegagen.efoyta.service.LoanProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanProductServiceImpl implements LoanProductService {

    private final LoanProductRepository loanProductRepository;
    @Override
    public List<LoanProductResponseDto> getAllLoanProducts() {
        List<LoanProduct> allLoanProducts = loanProductRepository.findAll();

        return allLoanProducts.stream().map(LoanProductMapper::mapToLoanProductResponse).toList();
    }

    @Override
    public List<SimpleLoanProductResponseDto> getAllSimpleLoanProducts() {
        List<LoanProduct> allLoanProducts = loanProductRepository.findAll();

        return allLoanProducts.stream().map(LoanProductMapper::mapToSimpleLoanProductResponse).toList();
    }

    @Override
    public SimpleLoanProductResponseDto createLoanProduct(LoanProductRequestDto requestDto) {
        loanProductRepository.findByLoanProductName(requestDto.getLoanProductName())
                .ifPresent(loanProduct -> {
                    throw new ResourceExistsException("There is already a loan product with this name: " + requestDto.getLoanProductName());
                });
        LoanProduct loanProduct = LoanProductMapper.mapToLoanProductEntity(requestDto);
        LoanProduct savedLoanProduct = loanProductRepository.save(loanProduct);

        return LoanProductMapper.mapToSimpleLoanProductResponse(savedLoanProduct);
    }

    @Override
    public SimpleLoanProductResponseDto updateLoanProduct(UUID loanProductId, LoanProductRequestDto requestDto) {
        LoanProduct existingLoanProduct = loanProductRepository.findByLoanProductId(loanProductId)
                .orElseThrow(()-> new ResourceNotFoundException("There is no loan product with this id: " + loanProductId));
        if(Objects.nonNull(requestDto.getLoanProductName()) && !Strings.isBlank(requestDto.getLoanProductName())){
            existingLoanProduct.setLoanProductName(requestDto.getLoanProductName());

            LoanProduct updatedLoanProduct = loanProductRepository.save(existingLoanProduct);
            return LoanProductMapper.mapToSimpleLoanProductResponse(updatedLoanProduct);
        }

        return LoanProductMapper.mapToSimpleLoanProductResponse(existingLoanProduct);
    }

    @Override
    public void deleteLoanProduct(UUID loanProductId) {
        LoanProduct existingLoanProduct = loanProductRepository.findByLoanProductId(loanProductId)
                .orElseThrow(()-> new ResourceNotFoundException("There is no loan product with this id: " + loanProductId));

        loanProductRepository.delete(existingLoanProduct);
    }
}
