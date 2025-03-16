package com.course.jpa.hibernate.sections.sections8.repo;

import com.course.jpa.hibernate.sections.sections8.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

    Optional<Course> findCourseBycId(Long courseId);
}
