package ru.nc.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nc.portal.model.Course;
import ru.nc.portal.model.Subject;
import ru.nc.portal.model.dto.CourseDTO;
import ru.nc.portal.model.dto.SubjectDTO;
import ru.nc.portal.repository.CourseRepository;
import ru.nc.portal.repository.SubjectRepository;
import ru.nc.portal.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "courseServiceImpl")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getALL() {
        return courseRepository.findAll();
//        return courseRepository.
    }
    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course createCourse(CourseDTO courseDTO) {
        Course course =new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        //return courseRepository.insert(courseDTO.getName(), courseDTO.getDescription());
        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getBySubjectId(Long subjectId) {
        return courseRepository.findAllBySubjectId(subjectId);
    }

    @Override
    public List<Course> getCoursesByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public List<Course> getAllByUserId(Long id) {
        return courseRepository.getAllByAuthorOfCourse(id);
    }
}
