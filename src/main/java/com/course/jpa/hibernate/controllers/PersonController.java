package com.course.jpa.hibernate.controllers;

import com.course.jpa.hibernate.dtos.PersonDto;
import com.course.jpa.hibernate.entities.Person;
import com.course.jpa.hibernate.mappers.PersonMapper;
import com.course.jpa.hibernate.repositories.PersonRepo;
import com.course.jpa.hibernate.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<PersonDto> getAllPerson() {
        return this.personService.getAllPerson();
    }
    @GetMapping("/request-per-page")
    public List<PersonDto> getAllPersonBasedOnPagination(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize) {
        return personService.getAllPersonViaPagination(pageNumber, pageSize);
    }

    @GetMapping("/get-person/{personId}")
    public PersonDto getSinglePersonDetails(@PathVariable("personId") Long personId) {
        return this.personService.getSinglePerson(personId);
    }

    @PostMapping("/create")
    public String createNewPerson(@RequestBody PersonDto personDto)
    {
       return this.personService.create(personDto);
    }

    @DeleteMapping("/delete-person/{personId}")
    public String deletePerson(@PathVariable("personId") Long personId) {
        return this.personService.deleteByUserId(personId);
    }
}
