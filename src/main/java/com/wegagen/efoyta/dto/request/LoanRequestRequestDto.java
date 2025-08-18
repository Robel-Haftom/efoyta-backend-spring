package com.wegagen.efoyta.dto.request;

import com.wegagen.efoyta.commons.enums.RequestStatus;
import com.wegagen.efoyta.entity.Customer;
import com.wegagen.efoyta.entity.LoanProduct;
import com.wegagen.efoyta.validation.OnCreate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestRequestDto {

    private String remark;

    @NotNull(message = "Answer this question", groups = {OnCreate.class})
    private Boolean haveYouVisitedTheBusinessAddress;

    @NotNull(message = "Answer this question", groups = {OnCreate.class})
    private Boolean isBusinessAddressComfortableForFollowUp;

    @NotNull(message = "Choose the loan product type", groups = {OnCreate.class})
    private UUID loanProductID;

    @NotNull(message = "Choose the loan product type")
    private String requestStatus;

    @NotNull(message = "Customer detail is required", groups = {OnCreate.class})
    private CustomerRequestDto customer;

    @NotNull(message = "Choose the loan product type")
    private Integer requestingBranchCode;

    @NotNull(message = "Customer detail is required", groups = {OnCreate.class})
    private String requestingBranchSenderName;

}
