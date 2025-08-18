package com.wegagen.efoyta.mapper;

import com.wegagen.efoyta.dto.response.LoginResponseDto;
import com.wegagen.efoyta.entity.User;

public class UserMapper {

    public static LoginResponseDto mapToLoginResponseDto(User user){
        return new LoginResponseDto(
                user.getFullName(),
                user.getEmployeeName(),
                user.getAccountType(),
                user.getBranchCode(),
                user.getWorkUnit(),
                user.getClusterId(),
                user.getPositionId(),
                user.getWorkstationId(),
                user.getPosition(),
                user.getClusterName()
        );
    }
}
