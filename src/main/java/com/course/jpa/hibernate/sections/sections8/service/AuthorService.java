package com.course.jpa.hibernate.sections.sections8.service;

import com.course.jpa.hibernate.sections.sections8.dto.AuthorDto;
import com.course.jpa.hibernate.sections.sections8.entity.Author;
import com.course.jpa.hibernate.sections.sections8.entity.Book;
import com.course.jpa.hibernate.sections.sections8.mapper.AuthorMapper;
import com.course.jpa.hibernate.sections.sections8.repo.AuthorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorMapper authorMapper, AuthorRepo authorRepo) {
        this.authorMapper = authorMapper;
        this.authorRepo = authorRepo;
    }

    public String saveAuthor(AuthorDto authorDto) {

        Author singleAuthor = authorMapper.dtoToEntity(authorDto);

        if(singleAuthor.getBooks() != null) {
            for(Book book: singleAuthor.getBooks()) {
                book.setAuthor(singleAuthor);
            }
        }

        authorRepo.save(singleAuthor);
        return "created";

    }

    public AuthorDto showAuthorDetails(Long authorId) {
        Author author = authorRepo.findAuthorByAuthorId(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        AuthorDto singleAuthorDetails = authorMapper.entityToDto(author);



        return singleAuthorDetails;
    }

    public String deleteParticularAuthorBook(Long authorId, Long bookId) {

        Author author = authorRepo.findAuthorByAuthorId(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        if(author.getBooks().isEmpty()) {
            throw new EntityNotFoundException("Currently no book available for current author id");
        }

        if(author.getBooks().stream().noneMatch(d -> d.getBookId().equals(bookId))) {
            throw new EntityNotFoundException("No book available with "+bookId+" associated with the passed author id");
        }

        author.getBooks().removeIf(book -> book.getBookId().equals(bookId));
        authorRepo.save(author);

        return "book deleted";

    }
}
