package com.wegagen.efoyta.service.Impl;

import com.wegagen.efoyta.dto.request.LoginRequestDto;
import com.wegagen.efoyta.dto.response.LoginResponseDto;
import com.wegagen.efoyta.exception.ResourceNotFoundException;
import com.wegagen.efoyta.repository.MssqlViewRepository;
import com.wegagen.efoyta.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MssqlViewRepository mssqlViewRepository;


    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        return mssqlViewRepository.findActiveUserByUsername(requestDto.getUserName())
                .map(row -> new LoginResponseDto(
                        (String) row.get("Fullname"),
                        (String) row.get("EmployeeName"),
                        row.get("Account_type") == null ? null : row.get("Account_type").toString(),
                        row.get("BRANCH_CODE") == null ? null : row.get("BRANCH_CODE").toString(),
                        (String) row.get("workunit"),
                        row.get("Cluster_id") == null ? null : row.get("Cluster_id").toString(),
                        row.get("position_id") == null ? null : row.get("position_id").toString(),
                        row.get("workstation_id") == null ? null : row.get("workstation_id").toString(),
                        (String) row.get("position"),
                        (String) row.get("Cluster_Name")
                ))
                .orElseThrow(() -> new ResourceNotFoundException("No user found with this user name: " + requestDto.getUserName()));
    }

    @Override
    public String countUsers() {
        long users = mssqlViewRepository.countActiveUsers();
        return Long.toString(users);
    }
}
