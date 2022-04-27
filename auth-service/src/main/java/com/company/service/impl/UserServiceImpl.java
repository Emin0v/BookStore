package com.company.service.impl;

import com.company.common.dto.UserResponseDto;
import com.company.common.dto.UserUpdateDto;
import com.company.entity.User;
import com.company.service.UserService;
import com.company.service.mapper.UserMapper;
import com.company.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto findByUserUuid(String uuid) {
        User user = userRepository.findByUuid(uuid).get();

        return userMapper.toUserRespDto(user);

    }

    @Override
    @Transactional
    public void update(String userUuid, UserUpdateDto dto) {
        User user = userRepository.findByUuid(userUuid).get();
        user.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();

    }

    @Override
    @Transactional
    public void delete(String uuid) {
        userRepository.delete(
                userRepository.findByUuid(uuid).get()
        );
    }

}
