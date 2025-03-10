package com.course.jpa.hibernate.mappers;

import com.course.jpa.hibernate.dtos.PersonDto;
import com.course.jpa.hibernate.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

//    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "personBirthDate", source = "personBirthDate", dateFormat="yyyy-MM-dd")
    @Mapping(target = "createdAt", source = "createdAt", ignore = true)
    Person dtoToEntity(PersonDto personDto);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss") // example format for timestamp
    PersonDto entityToDto(Person person);

}
