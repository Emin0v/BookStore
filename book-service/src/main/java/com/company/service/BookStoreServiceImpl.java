package com.company.service;

import com.company.common.client.UserServiceClient;
import com.company.constants.Category;
import com.company.domain.Book;
import com.company.common.dto.UserResponseDto;
import com.company.exceptions.DuplicateResourceException;
import com.company.exceptions.ResourceNotFoundException;
import com.company.model.dto.BookCreateDto;
import com.company.model.dto.BookResponseDto;
import com.company.model.dto.BookUpdateDto;
import com.company.repository.BookRepository;
import com.company.common.security.auth.service.SecurityService;
import com.company.service.mapper.BookMapper;
import com.company.service.search.SearchFilter;
import com.company.service.search.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookStoreServiceImpl implements BookStoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;
    private final SecurityService securityService;
    private final UserServiceClient userServiceClient;

    @Override
    @Transactional
    public void create(BookCreateDto bookCreateDto) {
        boolean check = bookRepository.existsByTitleAndCategory(bookCreateDto.getTitle(), Category.valueOf(bookCreateDto.getCategory()));
        if (check) throw new DuplicateResourceException("Book already exists:");

        Book book = bookMapper.mapToEntity(bookCreateDto);

        book.setUuid(UUID.randomUUID().toString());
        book.setUserUuid(getCurrentUserUuid());

        bookRepository.save(book);
    }

    @Override
    public void addBook(String uuid, int quantityToAdd) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id:" + uuid + " is not registered. Use addNewBook to register."));

        int totalCountAfterAdd = book.getTotalCount() + quantityToAdd;
        book.setTotalCount(totalCountAfterAdd);

        bookRepository.save(book);
    }

    @Override
    public BookResponseDto getBookById(String uuid) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id:" + uuid + " is not found."));

        return bookMapper.mapToDto(book);
    }


    @Override
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return this.mapToDtoList(books);
    }

    @Override
    public int getNumberOfBooksById(String uuid) {
        Optional<Book> book = bookRepository.findByUuid(uuid);

        //If book is present get Total Count else return 0
        return book.map(Book::getTotalCount).orElse(0);
    }

    @Override
    public void updateBook(String uuid, BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id:" + uuid + " is not registered."));

        book.setUserUuid(getCurrentUserUuid());
        book.setTitle(bookUpdateDto.getTitle());
        book.setCategory(bookUpdateDto.getCategory());
        book.setPrice(bookUpdateDto.getPrice());
        book.setTotalCount(bookUpdateDto.getTotalCount());

        bookRepository.save(book);
    }

    @Override
    public List<BookResponseDto> search(SearchFilter filter) {
        SearchSpecification searchSpecification = new SearchSpecification();
        searchSpecification.search(filter);
        List<Book> books = bookRepository.findAll(searchSpecification);

        return this.mapToDtoList(books);
    }

    private List<BookResponseDto> mapToDtoList(List<Book> books) {
        return books.stream().map(book -> {
            return BookResponseDto.builder()
                    .id(book.getId())
                    .uuid(book.getUuid())
                    .title(book.getTitle())
                    .category(book.getCategory())
                    .price(book.getPrice())
                    .totalCount(book.getTotalCount())
                    .createdAt(book.getCreatedAt())
                    .userResponseDto(userServiceClient.getByUuid(book.getUserUuid()).getBody())
                    .build();
        }).collect(Collectors.toList());
    }

    private String getCurrentUserUuid() {
        return securityService.getCurrentUserUuid().orElseThrow(
                () -> new ResourceNotFoundException("Current user uuid not found"));
    }

}
