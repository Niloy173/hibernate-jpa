package com.course.jpa.hibernate.sections.sections8.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDENT")
@DynamicUpdate
public class Student {

    @Id
    @GeneratedValue(generator="student_sequence")
    @SequenceGenerator(name="student_sequence",sequenceName="student_sequence", allocationSize=1)
    @Column(name = "ID")
    private Long sId;

    @Column(name = "NAME")
    private String sName;

    @Column(name = "AGE")
    private int sAge;

    @Column(name = "GENDER")
    private String sGender;

    @Column(name = "MOBILE")
    private String sMobile;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
       @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "ID")
    )
    @JsonManagedReference
    private Set<Course> courses = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Timestamp.from(Instant.now());
        }
    }


}
