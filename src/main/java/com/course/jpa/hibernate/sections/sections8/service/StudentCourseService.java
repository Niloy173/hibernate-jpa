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
            log.error("Error while saving student: ", e);
            throw new RuntimeException("Error enrolling student into course", e);
        }

        return "courseId "+courseId+" added for studentId : "+studentId;

    }

    public List<CourseDto> getStudentCoursesInformation(Long studentId) {

        Student student = studentRepo.findStudentBysId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

//        for(Course course: student.getCourses()) {
//            log.info("course name : {} ",course.getCName());
//        }

//        List<Object[]> registeredCourses = studentRepo.getAllRegisteredCoursesById(student.getSId());
        List<Course> registeredCourses = student.getCourses(); // using jpa approach

        if(registeredCourses.isEmpty()) {
            return new ArrayList<>();
        }

        List<CourseDto> courses = new ArrayList<>();

        registeredCourses.forEach(course -> {
            CourseDto formattedCourse = courseMapper.entityToDto(course);
            courses.add(formattedCourse);
        });


        return courses;
    }

    public List<StudentDto> getAllStudentsUnderSingleCourse(Long courseId) {

        Course course = courseRepo.findCourseBycId(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        List<Object[]> registeredStudents = studentRepo.getAllRegisteredStudentById(course.getCId());

        if(registeredStudents.isEmpty()) {
            return new ArrayList<>();
        }

        List<StudentDto> students = new ArrayList<>();

        registeredStudents.forEach(sId -> {
            Optional<Student> student = studentRepo.findStudentBysId((Long) sId[0]);
            StudentDto formattedStudent = studentMapper.entityToDto(student.get());
            students.add(formattedStudent);
        });

        return students;

    }

    public List<CourseDto> getAllUnregisteredCourse() {

        List<CourseDto> response = new ArrayList<>();
        List<Course> data = courseRepo.getAllUnregisteredCourseInfo();

        if(data.isEmpty()) {
            return new ArrayList<>();
        }

        data.forEach(course -> {
            CourseDto c = courseMapper.entityToDto(course);
            response.add(c);
        });
        return response;
    }
}
