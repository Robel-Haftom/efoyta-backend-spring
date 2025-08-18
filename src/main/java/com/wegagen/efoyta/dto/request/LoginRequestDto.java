package com.wegagen.efoyta.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotNull(message = "User name is required")
    private String userName;

    @NotNull(message = "Password is required")
    private String password;

}
