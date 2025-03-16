package com.course.jpa.hibernate.sections.sections8.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Author")
@DynamicUpdate
public class Author {

    @Id
    @GeneratedValue(generator="author_sequence")
    @SequenceGenerator(name="author_sequence",sequenceName="author_sequence", allocationSize=1)
    @Column(name = "ID")
    private Long authorId;

    @Column(name = "NAME")
    private String authorName;

    @Column(name = "NATIONALITY")
    private String authorNationality;

    @Column(name = "EMAIL")
    private String authorEmail;

    @Column(name = "GENRES")
    private String genres;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Timestamp.from(Instant.now());
        }
    }
}
