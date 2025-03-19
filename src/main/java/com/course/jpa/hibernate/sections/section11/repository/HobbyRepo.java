package com.course.jpa.hibernate.sections.section11.repository;

import com.course.jpa.hibernate.entities.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepo extends JpaRepository<Hobby,Long> {

    @Query(nativeQuery = true,
            value = "SELECT LOCKER.EMPLOYEE_SEQ.NEXTVAL FROM DUAL")
    Long generateId();
}
