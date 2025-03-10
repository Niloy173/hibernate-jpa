package com.course.jpa.hibernate.controllers;

import com.course.jpa.hibernate.dtos.PersonDto;
import com.course.jpa.hibernate.entities.Person;
import com.course.jpa.hibernate.mappers.PersonMapper;
import com.course.jpa.hibernate.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonMapper personMapper;
    private final PersonRepo personRepo;

    public PersonController( PersonMapper personMapper, PersonRepo personRepo) {
        this.personMapper = personMapper;
        this.personRepo = personRepo;
    }


    @PostMapping("/create")
    public String createNewPerson(@RequestBody PersonDto personDto)
    {
       Person p = personMapper.dtoToEntity(personDto);
        personRepo.save(p);

        return "saved";
    }
}
