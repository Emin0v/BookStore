package com.company.service;

import com.company.model.dto.BookCreateDto;
import com.company.model.dto.BookResponseDto;
import com.company.model.dto.BookUpdateDto;
import com.company.service.search.SearchFilter;

import java.util.List;

public interface BookStoreService {
    void create(BookCreateDto bookCreateDto);

    void addBook(String uuid, int quantityToAdd);

    BookResponseDto getBookById(String uuid);

    List<BookResponseDto> getAllBooks();

    int getNumberOfBooksById(String uuid);

    void updateBook(String uuid, BookUpdateDto bookUpdateDto);

    List<BookResponseDto> search(SearchFilter filter);

}
