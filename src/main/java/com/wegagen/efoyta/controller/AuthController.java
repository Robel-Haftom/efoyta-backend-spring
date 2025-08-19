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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto){
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @GetMapping()
    public ResponseEntity<String> count(){
        return ResponseEntity.ok(authService.count());
    }
}
