package com.wegagen.efoyta.dto.request;

import com.wegagen.efoyta.validation.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanProductRequestDto {

    @NotNull(message = "loan product name is required")
    private String loanProductName;
}
