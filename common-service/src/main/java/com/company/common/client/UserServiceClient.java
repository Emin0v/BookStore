package com.company.common.client;

import com.company.common.dto.RegisterDto;
import com.company.common.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("auth-service")
public interface UserServiceClient {

    @RequestMapping("/api/users/{uuid}")
    ResponseEntity<UserResponseDto> getByUuid(@PathVariable("uuid") String uuid);

    @PostMapping("/api/users/register")
    void register(@RequestBody RegisterDto reqDto);

}
