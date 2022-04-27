package com.company.service;

import com.company.common.dto.UserResponseDto;
import com.company.common.dto.UserUpdateDto;

public interface UserService {

    UserResponseDto findByUserUuid(String uuid);

    void update(String customerUuid, UserUpdateDto dto);

    void delete(String uuid);

}
