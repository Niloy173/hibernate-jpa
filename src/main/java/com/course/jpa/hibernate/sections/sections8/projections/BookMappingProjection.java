package com.course.jpa.hibernate.sections.sections8.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


public interface BookMappingProjection {

    Long getBookIdNumber();
    String getNameOfTheBook();
    String getPublishYear();
    int getTotalPage();
    String getType();
    Timestamp getCreatedAt();

}
