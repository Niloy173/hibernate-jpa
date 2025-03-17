package com.course.jpa.hibernate.sections.sections8.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Course")
@DynamicUpdate
public class Course {

    @Id
    @GeneratedValue(generator="course_sequence")
    @SequenceGenerator(name="course_sequence",sequenceName="course_sequence", allocationSize=1)
    @Column(name = "ID")
    private Long cId;

    @Column(name = "NAME")
    private String cName;

    @Column(name = "INSTRUCTOR_NAME")
    private String cInsName;

    @Column(name = "TOTAL_LECTURE")
    private int cTotalNoLec;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Student> students = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Timestamp.from(Instant.now());
        }
    }
}
