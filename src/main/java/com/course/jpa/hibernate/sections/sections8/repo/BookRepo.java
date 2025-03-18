package com.course.jpa.hibernate.sections.sections8.repo;

import com.course.jpa.hibernate.sections.sections8.entity.Book;
import com.course.jpa.hibernate.sections.sections8.projections.BookMappingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {

    @Query(
            "SELECT b.bookId AS bookIdNumber, " +
                    "b.bookName AS  nameOfTheBook, " +
                    "b.bookPublishYear AS publishYear, " +
                    "b.bookTotalPage AS totalPage, " +
                    "b.bookType AS type, " +
                    "b.createdAt as createdAt " +
                    "FROM Book b")
    List<BookMappingProjection> getAllBookList();

}
