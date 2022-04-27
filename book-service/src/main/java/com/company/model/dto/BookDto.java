package com.company.model.dto;

import com.company.constants.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    @ApiModelProperty(value="Book Unique Id")
    private Long id;

    @ApiModelProperty(value="Title of the book")
    private String title;

    @ApiModelProperty(value="Author of the book")
    private String author;

    @ApiModelProperty(value="Category of the book")
    private Category category;

    @ApiModelProperty(value = "Price of the book")
    @Min(value = 0, message = "Price should be positive value.")
    private float price;

    @ApiModelProperty(value="Copies of book available on the store")
    @Min(value = 0, message = "Total Count should be positive value.")
    private int totalCount;

}
