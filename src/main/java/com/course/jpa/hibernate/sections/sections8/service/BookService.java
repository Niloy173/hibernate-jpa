package com.course.jpa.hibernate.sections.sections8.service;

import com.course.jpa.hibernate.sections.sections8.dto.BookDto;
import com.course.jpa.hibernate.sections.sections8.projections.BookMappingProjection;
import com.course.jpa.hibernate.sections.sections8.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<BookDto> getAllBooks() {

        List<BookDto> response = new ArrayList<>();

        List<BookMappingProjection> allBooks = bookRepo.getAllBookList();

        for (BookMappingProjection book : allBooks) {
            BookDto singleBook = new BookDto();

            singleBook.setBookId(book.getBookIdNumber());
            singleBook.setBookName(book.getNameOfTheBook());
            singleBook.setBookType(book.getType());
            singleBook.setBookTotalPage(book.getTotalPage());
            singleBook.setBookPublishYear(book.getPublishYear());
            singleBook.setCreatedAt(book.getCreatedAt());

            response.add(singleBook);

        }

        return response;


    }
}
