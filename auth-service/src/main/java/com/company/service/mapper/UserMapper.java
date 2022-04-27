package com.company.service.mapper;

import com.company.common.dto.RegisterDto;
import com.company.common.dto.RegisterRequestDto;
import com.company.common.dto.UserResponseDto;
import com.company.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registerDtoToEntity(RegisterRequestDto registerRequestDto);

    UserResponseDto toUserRespDto(User user);

}
