package com.course.jpa.hibernate.sections.sections8.mapper;

import com.course.jpa.hibernate.sections.sections8.dto.AuthorDto;
import com.course.jpa.hibernate.sections.sections8.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {


    @Mapping(target = "authorId", ignore = true)
    //@Mapping(target = "books", source = "books")
    Author dtoToEntity(AuthorDto authorDto);

    //@Mapping(target = "books", source = "books")
    AuthorDto entityToDto(Author author);
}
