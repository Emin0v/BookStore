package com.company.domain;

import com.company.constants.Category;
import com.company.domain.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Book.BOOK_TABLE)
public class Book extends DateAudit {

    public static final String BOOK_TABLE = "books";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @NotNull
    private String title;

    private String userUuid;

    private Category category;

    @Min(value = 0, message = "Price should be positive value.")
    private float price;

    @Min(value = 0, message = "Total Count should be positive value.")
    private int totalCount;

}

