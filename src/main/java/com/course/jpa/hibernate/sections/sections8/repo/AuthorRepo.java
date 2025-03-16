package com.course.jpa.hibernate.sections.sections8.repo;

import com.course.jpa.hibernate.sections.sections8.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {

    Optional<Author> findAuthorByAuthorId(Long authorId);
}
