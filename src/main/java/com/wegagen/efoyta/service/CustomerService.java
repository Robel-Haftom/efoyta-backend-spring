package com.wegagen.efoyta.service;

import com.wegagen.efoyta.dto.response.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(UUID customerId);
}
