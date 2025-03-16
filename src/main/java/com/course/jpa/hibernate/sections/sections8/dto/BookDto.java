package com.course.jpa.hibernate.sections.sections8.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long bookId;
    private String bookName;
    private String bookPublishYear;
    private int bookTotalPage;
    private String bookType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long authorId; // This will be omitted only if it's null
    private Timestamp createdAt;
}
