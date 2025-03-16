package com.course.jpa.hibernate.repositories;

import com.course.jpa.hibernate.entities.Person;
import com.course.jpa.hibernate.projections.PersonProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {

    Optional<Person> findByPersonId(Long id);

    @Query("SELECT p.personId AS personId, " +
            "p.personAddress AS personAddress, "
            + "p.personEmail AS personEmail, "
            + "p.personGender AS personGender, "
            + "p.personFirstName AS personFirstName, "
            + "p.personPhoneNumber AS personPhoneNumber, "
            + "p.personBirthDate AS personBirthDate, "
            + "p.createdAt AS createdAt "
            + "FROM Person p WHERE p.personId = :id")
    Optional<PersonProjections> getPersonByPersonId(@Param("id") Long id);

    // if you use jpql query use alias properly to map projection method properly
    // or use Object[] array to type cast individually each person item whiie mapping in the service

}
