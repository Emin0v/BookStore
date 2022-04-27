package com.company.service.search;

import com.company.constants.Category;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

@Component
public class SearchMappingFilter {

    public SearchFilter setMapFilter(
            Integer page,
            Integer size,
            String title,
            String author,
            String category,
            Float price,
            Integer totalCount
    ) {
        SearchFilter filter = new SearchFilter();
        filter.setPage(page);
        filter.setSize(size);
        if (!ObjectUtils.isEmpty(author))
            filter.setAuthor(author);
        if (!ObjectUtils.isEmpty(title))
            filter.setTitle(title);
        if (!ObjectUtils.isEmpty(category)) {  //  EC    TECHNOLOGY
            try {
                filter.setCategoryId(Category.valueOf(category.toUpperCase()).getValue());
            } catch (IllegalArgumentException ex) {
                Arrays.stream(Category.values()).forEach(c -> {
                    if (c.name().contains(category.toUpperCase())) {
                        filter.setCategoryId(c.getValue());
                    }
                });
            }
        }
        if (!ObjectUtils.isEmpty(price))
            filter.setPrice(price);
        if (!ObjectUtils.isEmpty(totalCount))
            filter.setTotalCount(totalCount);
        return filter;
    }
}
