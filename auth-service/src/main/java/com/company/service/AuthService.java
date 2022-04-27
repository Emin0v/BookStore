package com.company.service;

import com.company.common.dto.JwtTokenDto;
import com.company.common.dto.LoginRequestDto;
import com.company.common.dto.RegisterRequestDto;

public interface AuthService {

    void register(RegisterRequestDto registerRequestDto);

    JwtTokenDto login(LoginRequestDto loginRequestDto);

}
