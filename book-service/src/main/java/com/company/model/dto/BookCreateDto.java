package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDto {

    private String title;

    private String category;

    @Min(value = 0, message = "Price should be positive value.")
    private float price;

    @Min(value = 0, message = "Total Count should be positive value.")
    private int totalCount;
}
