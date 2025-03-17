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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        Course course = courseRepo.findCourseBycId(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        //log.info("Course {} ",course);

        if(studentRepo.checkIfStudentAlreadyExistsInPassedCourse(studentId, courseId) > 0) {
            return "courseId "+courseId+" already registered for studentId : "+studentId;
        }

        try {

            student.getCourses().add(course);
            studentRepo.save(student);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return "courseId "+courseId+" added for studentId : "+studentId;

    }

    public List<CourseDto> getStudentCoursesInformation(Long id) {

        Student student = studentRepo.findStudentBysId(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        List<Object[]> registeredCourses = studentRepo.getAllRegisteredCoursesById(student.getSId());

        if(registeredCourses.isEmpty()) {
            return new ArrayList<>();
        }

        List<CourseDto> courses = new ArrayList<>();

        registeredCourses.forEach(cId -> {
            Optional<Course> course = courseRepo.findCourseBycId((Long) cId[0]);
            CourseDto formattedCourse = courseMapper.entityToDto(course.get());
            courses.add(formattedCourse);
        });


        return courses;
    }

    public List<StudentDto> getAllStudentsUnderSingleCourse(Long courseId) {

        return null;

    }
}
