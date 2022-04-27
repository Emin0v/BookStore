package com.company.common.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtTokenDto {

    private String authToken;
    private String refreshToken;

}
