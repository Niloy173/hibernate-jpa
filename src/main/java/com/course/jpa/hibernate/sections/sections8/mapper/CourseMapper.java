package com.course.jpa.hibernate.sections.sections8.mapper;

import com.course.jpa.hibernate.sections.sections8.dto.CourseDto;
import com.course.jpa.hibernate.sections.sections8.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "CId", ignore = true)
    Course dtoToEntity(CourseDto courseDto);

    CourseDto entityToDto(Course course);
}
