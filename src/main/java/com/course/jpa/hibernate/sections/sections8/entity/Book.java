package com.course.jpa.hibernate.sections.sections8.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
@DynamicUpdate
public class Book {

    @Id
    @GeneratedValue(generator="book_sequence")
    @SequenceGenerator(name="book_sequence",sequenceName="book_sequence", allocationSize=1)
    @Column(name = "ID")
    private Long bookId;

    @Column(name = "NAME")
    private String bookName;

    @Column(name = "PUBLISH_YEAR")
    private int bookPublishYear;

    @Column(name = "NUMBER_OF_PAGES")
    private int bookTotalPage;

    @Column(name = "TYPE")
    private String bookType;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    private Author author;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Timestamp.from(Instant.now());
        }
    }
}
