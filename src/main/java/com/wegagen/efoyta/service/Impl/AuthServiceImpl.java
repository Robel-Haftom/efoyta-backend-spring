package com.wegagen.efoyta.service.Impl;

import com.wegagen.efoyta.dto.request.LoginRequestDto;
import com.wegagen.efoyta.dto.response.LoginResponseDto;
import com.wegagen.efoyta.exception.InvalidCredentialsException;
import com.wegagen.efoyta.exception.ResourceNotFoundException;
import com.wegagen.efoyta.repository.MssqlViewRepository;
import com.wegagen.efoyta.security.JwtService;
import com.wegagen.efoyta.service.AuthService;
import com.wegagen.efoyta.security.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MssqlViewRepository mssqlViewRepository;
    private final PasswordService passwordService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;



    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        return mssqlViewRepository.findActiveUserByUsername(requestDto.getUserName())
                .map(row -> {
                    String userName = (String) row.get("UserName");
                    String storedHash = (String) row.get("hash");
                    String storedSalt = (String) row.get("salt");
                    String fullName = (String) row.get("Fullname");
                    String employeeName = (String) row.get("EmployeeName");
                    String workUnit = (String) row.get("workunit");
                    String workStationId = row.get("workstation_id").toString();
                    String role = row.get("Role").toString();

                    Map<String, String> claims = new HashMap<>();
                    claims.put("fullName", fullName);
                    claims.put("employeeNAme", employeeName);
                    claims.put("workUnit", workStationId);
                    claims.put("workStationId", workStationId);
                    claims.put("role", role);

                    if (!passwordService.verifyPassword(requestDto.getPassword(), storedHash, storedSalt)) {
                        throw new InvalidCredentialsException("Invalid username or password");
                    }
                    UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUserName());
                    String token = jwtService.generateToken(userDetails, claims);
                    return new LoginResponseDto(
                            userName,
                            fullName,
                            employeeName,
                            role,
                            row.get("Account_type") == null ? null : row.get("Account_type").toString(),
                            row.get("BRANCH_CODE") == null ? null : row.get("BRANCH_CODE").toString(),
                            workUnit,
                            row.get("Cluster_id") == null ? null : row.get("Cluster_id").toString(),
                            row.get("position_id") == null ? null : row.get("position_id").toString(),
                            row.get("workstation_id") == null ? null : workStationId,
                            (String) row.get("position"),
                            (String) row.get("Cluster_Name"),
                            token

                    );
                })
                .orElseThrow(() -> new ResourceNotFoundException("No user found with this user name: " + requestDto.getUserName()));
    }

    @Override
    public String count() {
        long count =  mssqlViewRepository.countActiveUsers();
        return Long.toString(count);
    }
}
