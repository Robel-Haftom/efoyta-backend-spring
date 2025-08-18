package com.wegagen.efoyta.service.Impl;

import com.wegagen.efoyta.dto.response.CustomerResponseDto;
import com.wegagen.efoyta.entity.Customer;
import com.wegagen.efoyta.exception.ResourceNotFoundException;
import com.wegagen.efoyta.mapper.CustomerMapper;
import com.wegagen.efoyta.repository.CustomerRepository;
import com.wegagen.efoyta.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();

        return allCustomers.stream().map(CustomerMapper::mapToCustomerResponseDto).toList();
    }

    @Override
    public CustomerResponseDto getCustomerById(UUID customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("No customer found with this id: " + customerId));
        return CustomerMapper.mapToCustomerResponseDto(customer);
    }
}
