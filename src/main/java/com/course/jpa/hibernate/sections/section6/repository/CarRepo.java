package com.course.jpa.hibernate.sections.section6.repository;

import com.course.jpa.hibernate.sections.section6.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {

    @Query(nativeQuery = true,
            value = "SELECT LOCKER.CAR_SEQUENCE.NEXTVAL FROM DUAL")
    Long generateId();


    Optional<Car> findCarByCarIdNumber(Long id);
}
