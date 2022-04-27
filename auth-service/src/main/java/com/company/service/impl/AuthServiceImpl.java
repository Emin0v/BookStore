package com.company.service.impl;

import com.company.common.client.UserServiceClient;
import com.company.common.dto.JwtTokenDto;
import com.company.common.dto.RegisterDto;
import com.company.common.dto.RegisterRequestDto;
import com.company.entity.User;
import com.company.common.exception.ConflictException;
import com.company.common.security.SecurityUtil;
import com.company.service.AuthService;
import com.company.common.dto.LoginRequestDto;
import com.company.repo.UserRepository;
import com.company.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static com.company.common.security.constants.UserAuthority.PUBLISHER;
import static com.company.common.security.constants.UserAuthority.USER;
import static com.company.common.security.constants.UserStatus.ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        Optional<User> userOptional = userRepository.findByEmail(registerRequestDto.getEmail());
        if (userOptional.isPresent()) {
            throw new ConflictException("Email already exist");
        }
        User user = userMapper.registerDtoToEntity(registerRequestDto);

        if (registerRequestDto.isPublisher())
            user.setAuthority(PUBLISHER);
        else user.setAuthority(USER);

        user.setStatus(ACTIVE);

        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        userRepository.save(user);

    }

    @Override
    public JwtTokenDto login(LoginRequestDto loginRequestDto) {
        String jwt = securityUtil.createAuthentication(loginRequestDto);

        return JwtTokenDto.builder().authToken(jwt).build();
    }
}
