package com.company.service.mapper;

import com.company.domain.Book;
import com.company.model.dto.BookCreateDto;
import com.company.model.dto.BookResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book mapToEntity(BookCreateDto bookCreateDto);

    BookResponseDto mapToDto(Book book);

}
