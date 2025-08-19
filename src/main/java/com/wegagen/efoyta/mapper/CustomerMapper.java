package com.wegagen.efoyta.mapper;

import com.wegagen.efoyta.commons.enums.Gender;
import com.wegagen.efoyta.dto.request.CustomerRequestDto;
import com.wegagen.efoyta.dto.response.CustomerResponseDto;
import com.wegagen.efoyta.dto.response.SimpleCustomerResponseDto;
import com.wegagen.efoyta.dto.response.SimpleLoanRequestResponseDto;
import com.wegagen.efoyta.entity.Customer;

public class CustomerMapper {

    public static Customer mapToCustomerEntity(CustomerRequestDto requestDto){
        return Customer.builder()
                .fullName(requestDto.getFullName())
                .phoneNumber(requestDto.getPhoneNumber())
                .accountNumber(requestDto.getAccountNumber())
                .accountBranch(requestDto.getAccountBranch())
                .gender(Gender.valueOf(requestDto.getGender().toUpperCase()))
                .build();
    }

    public static CustomerResponseDto mapToCustomerResponseDto(Customer customer){
        return new CustomerResponseDto(
                customer.getCustomerId().toString(),
                customer.getFullName(),
                customer.getAccountNumber(),
                customer.getAccountBranch(),
                customer.getPhoneNumber(),
                customer.getGender().toString(),
                customer.getLoanRequests().stream().map(LoanRequestMapper::mapToSimpleLoanRequestResponseDto).toList()
        );
    }

    public static SimpleCustomerResponseDto mapToSimpleCustomerResponseDto(Customer customer){
        return new SimpleCustomerResponseDto(
                customer.getCustomerId().toString(),
                customer.getFullName(),
                customer.getAccountNumber(),
                customer.getAccountBranch(),
                customer.getPhoneNumber(),
                customer.getGender().toString()
        );
    }
}
