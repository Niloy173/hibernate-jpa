package com.course.jpa.hibernate.sections.section6.mapper;


import com.course.jpa.hibernate.sections.section6.dtos.CarDto;
import com.course.jpa.hibernate.sections.section6.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "carIdNumber", ignore = true)
    @Mapping(target = "engine.engineSerialNumber", ignore = true)  // Prevents accidental updates
    Car dtoToEntity(CarDto carDto);

    CarDto entityToDto(Car car);
}
