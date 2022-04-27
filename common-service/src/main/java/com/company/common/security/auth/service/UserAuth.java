package com.company.common.security.auth.service;

import com.company.common.security.constants.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    private String uuid;
    private UserAuthority authority;
}
