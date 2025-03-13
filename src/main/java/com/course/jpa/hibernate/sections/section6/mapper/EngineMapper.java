package com.course.jpa.hibernate.sections.section6.mapper;

import com.course.jpa.hibernate.sections.section6.dtos.EngineDto;
import com.course.jpa.hibernate.sections.section6.entities.Engine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EngineMapper {

//    @Mapping(target = "carIdNumber", source = "car.carIdNumber")
    EngineDto entityToDto(Engine engine);

    @Mapping(target = "car", ignore = true) // To avoid circular reference
    Engine dtoToEntity(EngineDto engineDto);
}
