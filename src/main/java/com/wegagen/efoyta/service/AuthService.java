package com.wegagen.efoyta.service;

import com.wegagen.efoyta.dto.request.LoginRequestDto;
import com.wegagen.efoyta.dto.response.LoginResponseDto;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto requestDto);

    String countUsers();
}
