package com.company.service.search;

import lombok.Data;

@Data
public class SearchFilter {

    private Integer page;
    private Integer size;
    private String title;
    private String author;
    private Integer categoryId;
    private Float price;
    private Integer totalCount;

}
