package com.company.controller;

import com.company.model.dto.BookCreateDto;
import com.company.model.dto.BookResponseDto;
import com.company.model.dto.BookUpdateDto;
import com.company.service.BookStoreService;
import com.company.service.search.SearchMappingFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Api(value = "BookStore Controller", description = "BookStore REST Endpoints.")
public class BookStoreController {

    private final BookStoreService bookStoreService;
    private final SearchMappingFilter filter;

    @ApiOperation(value = "Add New Book")
    @PostMapping("/add-new-book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBook(@Valid @RequestBody BookCreateDto bookDto) {
        bookStoreService.create(bookDto);
    }

    @ApiOperation(value = "Add books")
    @PutMapping("/add-book/{uuid}/{quantityToAdd}")
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@PathVariable String uuid,
                        @PathVariable int quantityToAdd) {
        bookStoreService.addBook(uuid, quantityToAdd);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> search(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Float price,
            @RequestParam(required = false) Integer totalCount) {
        return ResponseEntity.ok(bookStoreService.search(
                filter.setMapFilter(page, size, title, author, category, price, totalCount)
        ));
    }

    @ApiOperation(value = "Get Book By Id")
    @GetMapping("/{uuid}")
    public BookResponseDto getBookById(@PathVariable String uuid) {
        return bookStoreService.getBookById(uuid);
    }

    @ApiOperation(value = "Get All Books")
    @GetMapping("/book-list")
    public List<BookResponseDto> getAllBooks() {
        return bookStoreService.getAllBooks();
    }

    @ApiOperation(value = "Get Number of books by Id")
    @GetMapping("/number-of-books/{uuid}")
    public int getNumberOfBooksById(@PathVariable String uuid) {
        return bookStoreService.getNumberOfBooksById(uuid);
    }

    @ApiOperation(value = "Update a book")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@RequestParam String uuid,
                           @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        bookStoreService.updateBook(uuid, bookUpdateDto);
    }

}
