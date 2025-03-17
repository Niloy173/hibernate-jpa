package com.course.jpa.hibernate.sections.sections8.repo;

import com.course.jpa.hibernate.sections.sections8.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    Optional<Student> findStudentBysId(Long studentId);

    @Query(value = "SELECT count(*) FROM STUDENT_COURSES SC " +
            "WHERE SC.STUDENT_ID = :sId AND SC.COURSE_ID = :cId", nativeQuery = true)
    int checkIfStudentAlreadyExistsInPassedCourse(@Param("sId") Long sId, @Param("cId") Long cId);

    @Query(value = "SELECT SC.COURSE_ID FROM STUDENT_COURSES SC " +
            "WHERE SC.STUDENT_ID = :sId", nativeQuery = true)
    List<Object[]> getAllRegisteredCoursesById(@Param("sId") Long sId);
}
