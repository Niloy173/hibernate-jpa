package com.course.jpa.hibernate.sections.sections8.mapper;

import com.course.jpa.hibernate.sections.sections8.dto.StudentDto;
import com.course.jpa.hibernate.sections.sections8.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "SId",ignore = true)
    Student dtoToEntity(StudentDto studentDto);

    StudentDto entityToDto(Student student);
}
