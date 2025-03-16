package com.course.jpa.hibernate.sections.section3.h2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {

    private long id;
    private String name;
    private String description;
}
