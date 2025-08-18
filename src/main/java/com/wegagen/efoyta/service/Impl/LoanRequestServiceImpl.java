package com.wegagen.efoyta.service.Impl;

import com.wegagen.efoyta.commons.enums.Gender;
import com.wegagen.efoyta.commons.enums.RequestStatus;
import com.wegagen.efoyta.dto.request.LoanRequestRequestDto;
import com.wegagen.efoyta.dto.response.LoanRequestResponseDto;
import com.wegagen.efoyta.entity.Customer;
import com.wegagen.efoyta.entity.LoanProduct;
import com.wegagen.efoyta.entity.LoanRequest;
import com.wegagen.efoyta.exception.ResourceNotFoundException;
import com.wegagen.efoyta.mapper.CustomerMapper;
import com.wegagen.efoyta.mapper.LoanRequestMapper;
import com.wegagen.efoyta.repository.CustomerRepository;
import com.wegagen.efoyta.repository.LoanProductRepository;
import com.wegagen.efoyta.repository.LoanRequestRepository;
import com.wegagen.efoyta.service.LoanRequestService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanRequestServiceImpl implements LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;
    private final CustomerRepository customerRepository;
    private final LoanProductRepository loanProductRepository;

    @Override
    public List<LoanRequestResponseDto> getAllLoanRequests() {
        List<LoanRequest> allLoanRequests = loanRequestRepository.findAll();

        return allLoanRequests.stream().map(LoanRequestMapper::mapToLoanRequestResponseDto).toList();
    }

    @Override
    public LoanRequestResponseDto getLoanRequestById(UUID loanRequestId) {
        LoanRequest loanRequest = loanRequestRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("No loan request found with this id: " + loanRequestId));

        return LoanRequestMapper.mapToLoanRequestResponseDto(loanRequest);
    }

    @Override
    public LoanRequestResponseDto createLoanRequest(LoanRequestRequestDto requestDto) {
        LoanProduct loanProduct = loanProductRepository.findByLoanProductId(requestDto.getLoanProductID())
                .orElseThrow(() -> new ResourceNotFoundException("No loan product found with this id: " + requestDto.getLoanProductID()));

        LoanRequest loanRequest = LoanRequestMapper.mapToLoanRequestEntity(requestDto);

        customerRepository.findByAccountNumber(requestDto.getCustomer().getAccountNumber())
                .ifPresentOrElse(loanRequest::setCustomer,
                        () -> {
                            Customer customer = CustomerMapper.mapToCustomerEntity(requestDto.getCustomer());
                            Customer savedCustomer = customerRepository.save(customer);
                            loanRequest.setCustomer(savedCustomer);
                        });

        loanRequest.setLoanProduct(loanProduct);
        //todo set the branch code and sender name here

        LoanRequest savedLoanRequest = loanRequestRepository.save(loanRequest);

        return LoanRequestMapper.mapToLoanRequestResponseDto(savedLoanRequest);
    }

    @Override
    public LoanRequestResponseDto updateLoanRequest(UUID loanRequestId, LoanRequestRequestDto requestDto) {
        LoanRequest existingLoanRequest = loanRequestRepository.findByLoanRequestId(loanRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("No loan request found with this id: " + loanRequestId));

        if(Objects.nonNull(requestDto.getCustomer().getAccountNumber()) && !Strings.isBlank(requestDto.getCustomer().getAccountNumber())){
            existingLoanRequest.getCustomer().setAccountNumber(requestDto.getCustomer().getAccountNumber());
        }
        if(Objects.nonNull(requestDto.getCustomer().getPhoneNumber()) && !Strings.isBlank(requestDto.getCustomer().getPhoneNumber())){
            existingLoanRequest.getCustomer().setPhoneNumber(requestDto.getCustomer().getPhoneNumber());
        }
        if(Objects.nonNull(requestDto.getCustomer().getGender()) && !Strings.isBlank(requestDto.getCustomer().getGender())){
            existingLoanRequest.getCustomer().setGender(Gender.valueOf(requestDto.getCustomer().getGender()));
        }
        if(Objects.nonNull(requestDto.getLoanProductID()) && !Strings.isBlank(requestDto.getLoanProductID().toString())){
            LoanProduct loanProduct = loanProductRepository.findByLoanProductId(requestDto.getLoanProductID())
                            .orElseThrow(()-> new ResourceNotFoundException("There is no loan product with this id: " + requestDto.getLoanProductID()));
            existingLoanRequest.setLoanProduct(loanProduct);
        }
        if(Objects.nonNull(requestDto.getRemark())& !Strings.isBlank(requestDto.getRemark())){
            existingLoanRequest.setRemark(requestDto.getRemark());
        }
        if(Objects.nonNull(requestDto.getHaveYouVisitedTheBusinessAddress())){
            existingLoanRequest.setHaveYouVisitedTheBusinessAddress(requestDto.getHaveYouVisitedTheBusinessAddress());
        }
        if(Objects.nonNull(requestDto.getIsBusinessAddressComfortableForFollowUp())){
            existingLoanRequest.setIsBusinessAddressComfortableForFollowUp(requestDto.getIsBusinessAddressComfortableForFollowUp());
        }
        if(Objects.nonNull(requestDto.getRequestStatus()) && !Strings.isBlank(requestDto.getRequestStatus())){
            existingLoanRequest.setRequestStatus(RequestStatus.valueOf(requestDto.getRequestStatus()));
        }

        LoanRequest updatedLoanRequest = loanRequestRepository.save(existingLoanRequest);

        return LoanRequestMapper.mapToLoanRequestResponseDto(updatedLoanRequest);
    }

}
