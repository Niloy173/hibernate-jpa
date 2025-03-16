package com.course.jpa.hibernate.sections.sections8.service;

import com.course.jpa.hibernate.sections.sections8.dto.CourseDto;
import com.course.jpa.hibernate.sections.sections8.dto.StudentDto;
import com.course.jpa.hibernate.sections.sections8.entity.Course;
import com.course.jpa.hibernate.sections.sections8.entity.Student;
import com.course.jpa.hibernate.sections.sections8.mapper.CourseMapper;
import com.course.jpa.hibernate.sections.sections8.mapper.StudentMapper;
import com.course.jpa.hibernate.sections.sections8.repo.CourseRepo;
import com.course.jpa.hibernate.sections.sections8.repo.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentCourseService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    public StudentCourseService(StudentRepo studentRepo, CourseRepo courseRepo, StudentMapper studentMapper, CourseMapper courseMapper) {
        this.studentRepo = studentRepo;
        this.studentMapper = studentMapper;
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
    }

    public String saveStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        studentRepo.save(student);
        return "new student saved";
    }

    public String saveCourse(CourseDto courseDto) {
        Course course = courseMapper.dtoToEntity(courseDto);
        courseRepo.save(course);
        return "new course saved";
    }

    public String enrollStudentIntoCourse(Long studentId, Long courseId) {

        Student student = studentRepo.findStudentBysId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        log.info("Student {} ",student);

        Course course = courseRepo.findCourseBycId(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        log.info("Course {} ",course);

        try {

            student.getCourses().add(course);
            studentRepo.save(student);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "courseId "+courseId+" added for studentId : "+studentId;

    }

    public StudentDto getStudentCourseInformation(Long id) {

        Student student = studentRepo.findStudentBysId(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

                StudentDto response = studentMapper.entityToDto(student);

        Set<Long> courseIds = student.getCourses().stream()
                .map(Course::getCId)
                .collect(Collectors.toSet());

        response.setCourseIds(courseIds);

        return response;
    }
}
