package com.course.jpa.hibernate.sections.sections8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long sId;
    private String sName;
    private int sAge;
    private String sGender;
    private String sMobile;
    private Timestamp createdAt;

    // Instead of including entire CourseDto objects, just use their IDs
    private Set<Long> courseIds = new HashSet<>();
}
