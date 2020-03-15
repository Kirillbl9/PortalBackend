package ru.nc.portal.service;

import ru.nc.portal.model.Course;
import ru.nc.portal.model.Subject;
import ru.nc.portal.model.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    List<Course> getALL();
    Course getById(Long id);
    Course createCourse(CourseDTO courseDTO);
    List<Course> getBySubjectId(Long subjectId);
    List<Course> getCoursesByName(String name);
    List<Course> getAllByUserId(Long id);
}

