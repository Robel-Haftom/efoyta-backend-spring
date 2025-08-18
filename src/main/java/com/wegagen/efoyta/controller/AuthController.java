package com.wegagen.efoyta.controller;

import com.wegagen.efoyta.dto.request.LoginRequestDto;
import com.wegagen.efoyta.dto.response.LoginResponseDto;
import com.wegagen.efoyta.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping()
    public ResponseEntity<String> countUsers(){
        return ResponseEntity.ok(authService.countUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto){
        return ResponseEntity.ok(authService.login(requestDto));
    }
}
