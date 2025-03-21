package com.course.jpa.hibernate.sections.sections8.controller;

import com.course.jpa.hibernate.sections.sections8.dto.AuthorDto;
import com.course.jpa.hibernate.sections.sections8.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public List<AuthorDto> getAllAuthor() {
        return authorService.getAllAuthor();
    }

    @GetMapping("/author-details/{authorId}")
    public AuthorDto getAuthorDetails(@PathVariable("authorId") Long authorId) {
        return  authorService.showAuthorDetails(authorId);
    }

    @PostMapping("/create")
    public String createNewAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.saveAuthor(authorDto);
    }

    @PostMapping("/delete-author-book")
    public String deleteParticularBook(@RequestParam("authorId") Long authorId,
                                       @RequestParam("bookId") Long bookId) {
        return authorService.deleteParticularAuthorBook(authorId,bookId);
    }
}
