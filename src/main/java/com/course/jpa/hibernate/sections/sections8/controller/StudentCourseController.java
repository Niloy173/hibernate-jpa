package com.course.jpa.hibernate.sections.sections8.controller;

import com.course.jpa.hibernate.sections.sections8.dto.CourseDto;
import com.course.jpa.hibernate.sections.sections8.dto.StudentDto;
import com.course.jpa.hibernate.sections.sections8.service.StudentCourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController {

    private final StudentCourseService service;

    public StudentCourseController(StudentCourseService service) {
        this.service = service;
    }

    @PostMapping("/student/create")
    public String createNewStudent(@RequestBody StudentDto studentDto) {
        return service.saveStudent(studentDto);
    }

    @PostMapping("/course/create")
    public String createNewCourse(@RequestBody CourseDto courseDto) {
        return service.saveCourse(courseDto);
    }

    @PostMapping("/enroll-student")
    public String enrollStudentInCourse(
            @RequestParam("studentId") Long studentId,
            @RequestParam("courseId") Long courseId
    ) {
        return service.enrollStudentIntoCourse(studentId,courseId);
    }

    @GetMapping("/student/{id}/registered-courses")
    public List<CourseDto> getStudentRegisteredCourseInformation(@PathVariable("id") Long id) {
        return service.getStudentCoursesInformation(id);
    }

    @GetMapping("/course/{id}/registered-students")
    public List<StudentDto> getRegStudentUnderSingleCourse(@PathVariable("id") Long id) {
        return service.getAllStudentsUnderSingleCourse(id);
    }
}
