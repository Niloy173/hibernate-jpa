package com.course.jpa.hibernate.sections.sections8.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long cId;
    private String cName;
    private String cInsName;
    private int cTotalNoLec;
    private Timestamp createdAt;

    // Instead of including entire StudentDto objects, just use their IDs
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Long> studentIds = new ArrayList<>();
}
