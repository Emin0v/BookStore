package com.company.service.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria<E1, E2> {

    private String key;
    private E1 value1;
    private E2 value2;
    private SearchOperation operation1;
    private SearchOperation operation2;

    public SearchCriteria(String key, E1 value1, SearchOperation searchOperation1) {
        this.key = key;
        this.value1 = value1;
        this.operation1 = searchOperation1;
    }

    public SearchCriteria(String key, E1 value1, E2 value2, SearchOperation searchOperation1) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.operation1 = searchOperation1;
    }

    public SearchCriteria(String key, E1 value1, SearchOperation searchOperation1, SearchOperation searchOperation2) {
        this.key = key;
        this.value1 = value1;
        this.operation1 = searchOperation1;
        this.operation2 = searchOperation2;
    }
}
