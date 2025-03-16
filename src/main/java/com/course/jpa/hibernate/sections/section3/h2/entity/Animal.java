package com.course.jpa.hibernate.sections.section3.h2.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private long animalId;
    private String animalName;
    private String animalDescription;
}
