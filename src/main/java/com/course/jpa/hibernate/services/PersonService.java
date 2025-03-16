package com.course.jpa.hibernate.services;

import com.course.jpa.hibernate.dtos.PersonDto;
import com.course.jpa.hibernate.entities.Person;
import com.course.jpa.hibernate.mappers.PersonMapper;
import com.course.jpa.hibernate.projections.PersonProjections;
import com.course.jpa.hibernate.repositories.PersonRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonMapper personMapper;
    private final PersonRepo personRepo;

    @Autowired
    private EntityManager em;

    public PersonService(PersonMapper personMapper, PersonRepo personRepo) {
        this.personMapper = personMapper;
        this.personRepo = personRepo;
    }

    public String create(PersonDto personDto) {

        Person p = personMapper.dtoToEntity(personDto);
        personRepo.save(p);

        return "saved";
    }

    public PersonDto getSinglePerson(Long id) {

        PersonProjections person = personRepo.getPersonByPersonId(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));


        PersonDto mappedPerson = new PersonDto();
        mappedPerson.setPersonId(person.getPersonId());
        mappedPerson.setPersonAddress(person.getPersonAddress());
        mappedPerson.setPersonEmail(person.getPersonEmail());
        mappedPerson.setPersonGender(person.getPersonGender());
        mappedPerson.setPersonFirstName(person.getPersonFirstName());
        mappedPerson.setPersonPhoneNumber(person.getPersonPhoneNumber());
        mappedPerson.setPersonBirthDate(person.getPersonBirthDate().toString());
        mappedPerson.setCreatedAt(person.getCreatedAt().toString());

        return mappedPerson;
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteByUserId(Long id) {
        Person p = personRepo.findByPersonId(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        em.remove(p);
        return "deleted";
    }
}
