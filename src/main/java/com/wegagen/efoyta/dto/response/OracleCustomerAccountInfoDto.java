package com.wegagen.efoyta.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OracleCustomerAccountInfoDto {

	private String customerName;
	private String gender;
	private String branchCode;
	private String branchName;
	private String mobileNumber;
	private String customerAccountNumber;
	private String customerNumber;
}


