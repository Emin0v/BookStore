package com.company.model.dto;


import com.company.common.dto.UserResponseDto;
import com.company.constants.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {

    private Long id;
    private String uuid;
    private String title;
    private Category category;
    private float price;
    private int totalCount;
    private Instant createdAt;

    private UserResponseDto userResponseDto;
}
