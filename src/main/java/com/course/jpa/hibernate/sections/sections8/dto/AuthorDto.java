package com.course.jpa.hibernate.sections.sections8.dto;

import com.course.jpa.hibernate.sections.sections8.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long authorId;
    private String authorName;
    private String authorNationality;
    private String authorEmail;
    private String genres;
    private List<BookDto> books = new ArrayList<>();
    private Timestamp createdAt;
}
