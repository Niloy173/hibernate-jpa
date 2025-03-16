package com.course.jpa.hibernate.services;

import com.course.jpa.hibernate.dtos.PersonDto;
import com.course.jpa.hibernate.entities.Person;
import com.course.jpa.hibernate.projections.PersonProjections;
import com.course.jpa.hibernate.repositories.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    private PersonService personService;

    @Test
    public void checkParticularUserExistOrNot() {

        Long personId = 2L;

        // Mock the response from the repository
        PersonProjections mockPerson = mock(PersonProjections.class);
        when(mockPerson.getPersonId()).thenReturn(personId);
        when(mockPerson.getPersonAddress()).thenReturn("123 Street");
        when(mockPerson.getPersonEmail()).thenReturn("test@example.com");
        when(mockPerson.getPersonGender()).thenReturn("Male");
        when(mockPerson.getPersonFirstName()).thenReturn("John");
        when(mockPerson.getPersonPhoneNumber()).thenReturn("1234567890");
        when(mockPerson.getPersonBirthDate()).thenReturn(LocalDate.of(1990, 1, 1));
        when(mockPerson.getCreatedAt()).thenReturn(Timestamp.valueOf("2024-01-01 10:00:00"));

        // Tell the mock repository to return mockPerson when called with personId
        when(personRepo.getPersonByPersonId(personId)).thenReturn(Optional.of(mockPerson));


        PersonDto p = personService.getSinglePerson(personId);


        assertThat(p).isNotNull();
        assertThat(p.getPersonId()).isEqualTo(personId);
        assertThat(p.getPersonEmail()).contains("@");

    }
}
