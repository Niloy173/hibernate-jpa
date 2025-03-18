package com.course.jpa.hibernate.sections.sections8.mapper;

import com.course.jpa.hibernate.sections.sections8.dto.BookDto;
import com.course.jpa.hibernate.sections.sections8.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book dtoToEntity(BookDto bookDto);

    BookDto entityToDto(Book book);


    List<Book> dtoToEntityList(List<BookDto> bookDtos);


    //@Mapping(target = "authorId", source = "authorId", ignore = true)
    List<BookDto> entityToDtoList(List<Book> books);
}
