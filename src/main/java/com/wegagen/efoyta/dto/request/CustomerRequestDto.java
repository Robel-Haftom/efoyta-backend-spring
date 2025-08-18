package com.wegagen.efoyta.dto.request;

import com.wegagen.efoyta.validation.OnCreate;
import com.wegagen.efoyta.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotNull(message = "Customer full name is required", groups = {OnCreate.class})
    private String fullName;

    @NotNull(message = "Customer account number is required", groups = {OnCreate.class})
    private String accountNumber;

    @NotNull(message = "Customer account branch is required", groups = {OnCreate.class})
    private String accountBranch;

    @NotNull(message = "Customer phone number is required", groups = {OnCreate.class})
    @Pattern(
            regexp = "^(\\+251|0)(7\\d{8}|9\\d{8}|[1-5]\\d{8})$",
            message = "Invalid phone number",
            groups = {OnCreate.class, OnUpdate.class}
    )
    private String phoneNumber;

    @NotNull(message = "Customer gender is required", groups = {OnCreate.class})
    @Pattern(regexp = "MALE|FEMALE", message = "Gender must be either MALE or FEMALE")
    private String gender;

}
