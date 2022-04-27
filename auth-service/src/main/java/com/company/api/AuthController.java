package com.company.api;

import com.company.common.dto.JwtTokenDto;
import com.company.common.dto.LoginRequestDto;
import com.company.common.dto.RegisterRequestDto;
import com.company.service.AuthService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.AuthCtrl.CTRL)
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Validated RegisterRequestDto registerRequestDto) {
        log.trace("Register user: {}", registerRequestDto);
        authService.register(registerRequestDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody @Validated LoginRequestDto loginRequestDto) {
        log.trace("Login user: {}", loginRequestDto);
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


}