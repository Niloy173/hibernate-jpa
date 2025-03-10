package com.course.jpa.hibernate.repositories;

import com.course.jpa.hibernate.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {
}
