package com.course.jpa.hibernate.sections.sections8.service;

import com.course.jpa.hibernate.sections.sections8.dto.CourseDto;
import com.course.jpa.hibernate.sections.sections8.entity.Course;
import com.course.jpa.hibernate.sections.sections8.mapper.CourseMapper;
import com.course.jpa.hibernate.sections.sections8.repo.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepo courseRepo, CourseMapper courseMapper) {
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
    }

    public List<CourseDto> getAllCoursesByPassedNoOfLec(int lectureNoToPass) {
        List<CourseDto> response = new ArrayList<>();

        List<Course> data = courseRepo.getAllCoursesByTotalNumberOfLecture(lectureNoToPass);

        if(data.isEmpty()) {
            return new ArrayList<>();
        }

        data.forEach(c -> {
            CourseDto mappedResponse = courseMapper.entityToDto(c);
            response.add(mappedResponse);
        });

        return response;
    }
}
