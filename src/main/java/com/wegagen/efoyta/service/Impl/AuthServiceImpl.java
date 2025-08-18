package com.wegagen.efoyta.service.Impl;

import com.wegagen.efoyta.dto.request.LoginRequestDto;
import com.wegagen.efoyta.dto.response.LoginResponseDto;
import com.wegagen.efoyta.entity.User;
import com.wegagen.efoyta.exception.ResourceNotFoundException;
import com.wegagen.efoyta.mapper.UserMapper;
import com.wegagen.efoyta.repository.MssqlViewRepository;
import com.wegagen.efoyta.repository.UserRepository;
import com.wegagen.efoyta.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final MssqlViewRepository mssqlViewRepository;


    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        User user = userRepository.findActiveUser(requestDto.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("No user found with this user name: " + requestDto.getUserName()));
        return UserMapper.mapToLoginResponseDto(user);
    }

    @Override
    public String countUsers() {
        long users = mssqlViewRepository.countActiveUsers();
        return Long.toString(users);
    }
}
