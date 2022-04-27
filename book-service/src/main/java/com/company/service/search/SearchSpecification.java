package com.company.service.search;

import com.company.domain.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SearchSpecification implements Specification<Book> {

    private static final long serialVersionUID = -6367804240777724876L;

    private List<SearchCriteria> criteriaList = new ArrayList<>();

    public void search(SearchFilter filter) {
        if (!ObjectUtils.isEmpty(filter.getTitle())) {
            add(new SearchCriteria("title", filter.getTitle(), SearchOperation.MATCH_START));
        }
        if (!ObjectUtils.isEmpty(filter.getAuthor())) {
            add(new SearchCriteria("author", filter.getAuthor(), SearchOperation.MATCH_START));
        }
        if (!ObjectUtils.isEmpty(filter.getPrice())) {
            add(new SearchCriteria("price", filter.getPrice(), SearchOperation.EQUAL));
        }
        if (!ObjectUtils.isEmpty(filter.getTotalCount())) {
            add(new SearchCriteria("totalCount", filter.getTotalCount(), SearchOperation.EQUAL));
        }
        if (!ObjectUtils.isEmpty(filter.getCategoryId())) {
            add(new SearchCriteria("category", filter.getCategoryId(), SearchOperation.EQUAL));
        }

    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : criteriaList) {
            if (criteria.getOperation1().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue1()));
            } else if (criteria.getOperation1().equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue1().toString().toLowerCase() + "%"));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private void add(SearchCriteria searchCriteria) {
        criteriaList.add(searchCriteria);
    }

    @Override
    public Specification<Book> and(Specification<Book> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Book> or(Specification<Book> other) {
        return Specification.super.or(other);
    }
}
