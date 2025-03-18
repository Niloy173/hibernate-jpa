package com.course.jpa.hibernate.sections.sections8.service;

import com.course.jpa.hibernate.sections.sections8.dto.AuthorDto;
import com.course.jpa.hibernate.sections.sections8.dto.BookDto;
import com.course.jpa.hibernate.sections.sections8.entity.Author;
import com.course.jpa.hibernate.sections.sections8.entity.Book;
import com.course.jpa.hibernate.sections.sections8.mapper.AuthorMapper;
import com.course.jpa.hibernate.sections.sections8.mapper.BookMapper;
import com.course.jpa.hibernate.sections.sections8.repo.AuthorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepo authorRepo;
    private final BookMapper bookMapper;

    public AuthorService(AuthorMapper authorMapper, AuthorRepo authorRepo, BookMapper bookMapper) {
        this.authorMapper = authorMapper;
        this.authorRepo = authorRepo;
        this.bookMapper = bookMapper;
    }

    public String saveAuthor(AuthorDto authorDto) {

        // Check if author exists by unique identifiers like email or author name
        Optional<Author> existingAuthorOpt = authorRepo.findByAuthorEmailOrAuthorName(
                authorDto.getAuthorEmail(), authorDto.getAuthorName()
        );

        Author author;

        if (existingAuthorOpt.isPresent()) {
            author = existingAuthorOpt.get(); // Existing author found, update details
            author.setAuthorName(authorDto.getAuthorName());
            author.setAuthorNationality(authorDto.getAuthorNationality());
            author.setAuthorEmail(authorDto.getAuthorEmail());
            author.setGenres(authorDto.getGenres());

            // Handle existing books
            if (authorDto.getBooks() != null && !authorDto.getBooks().isEmpty()) {
                List<Book> existingBooks = author.getBooks();

                // Keep track of new books to add
                List<Book> newBooks = new ArrayList<>();

                for (BookDto bookDto : authorDto.getBooks()) {
                    boolean exists = false;

                    for (Book existingBook : existingBooks) {
                        if (existingBook.getBookId().equals(bookDto.getBookId())) {
                            // Update the existing book
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        // New book, add to the list
                        Book newBook = bookMapper.dtoToEntity(bookDto);
                        newBook.setAuthor(author);
                        newBooks.add(newBook);
                    }
                }

                // Add new books to the existing list
                existingBooks.addAll(newBooks);
            }

        } else {
            // New author scenario
            author = authorMapper.dtoToEntity(authorDto);

            if (author.getBooks() != null) {

                Author finalAuthor = author;
                author.getBooks().forEach(book -> book.setAuthor(finalAuthor));
            }
        }

        authorRepo.save(author); // Save the author (and books, if new ones are added)

        return existingAuthorOpt.isPresent() ? "Author updated successfully" : "New author created successfully";

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
