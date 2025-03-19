package com.course.jpa.hibernate.sections.sections8.repo;

import com.course.jpa.hibernate.sections.sections8.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

    Optional<Course> findCourseBycId(Long courseId);

    @Query("Select c from Course c where c.students is empty")
    List<Course> getAllUnregisteredCourseInfo();

    @Query("select c from Course c where c.cTotalNoLec > :noOfLec order by c.cTotalNoLec desc")
    List<Course> getAllCoursesByTotalNumberOfLecture(@Param("noOfLec") int noOfLec);
}
