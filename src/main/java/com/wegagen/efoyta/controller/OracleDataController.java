package com.wegagen.efoyta.controller;

import com.wegagen.efoyta.dto.response.OracleCustomerAccountInfoDto;
import com.wegagen.efoyta.repository.OracleViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/oracle")
@RequiredArgsConstructor
public class OracleDataController {

	private final OracleViewRepository oracleViewRepository;

	@GetMapping("/customer-account-info")
	public ResponseEntity<List<OracleCustomerAccountInfoDto>> getCustomerAccountInfo() {
		List<OracleCustomerAccountInfoDto> data = oracleViewRepository.findCustomerAccountInfos();
		return ResponseEntity.ok(data);
	}


	@GetMapping("/customer-account-info/{accountNumber}")
	public ResponseEntity<OracleCustomerAccountInfoDto> getCustomerAccountInfoByAccountNumber(
			@PathVariable("accountNumber") String accountNumber
	) {
		return oracleViewRepository.findCustomerAccountInfoByAccountNumber(accountNumber)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}


