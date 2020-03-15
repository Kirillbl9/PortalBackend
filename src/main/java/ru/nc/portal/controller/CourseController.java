package ru.nc.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.nc.portal.model.Course;
import org.springframework.web.bind.annotation.*;
import ru.nc.portal.model.Subject;
import ru.nc.portal.model.User;
import ru.nc.portal.model.dto.CourseDTO;
import ru.nc.portal.model.dto.SubjectDTO;
import ru.nc.portal.repository.CourseRepository;
import ru.nc.portal.repository.SubjectRepository;
import ru.nc.portal.repository.UserRepository;
import ru.nc.portal.service.CourseService;
import ru.nc.portal.service.SubjectService;
import ru.nc.portal.service.UserService;

import javax.validation.Valid;
import java.io.Console;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class CourseController {
    @Autowired
    UserService userService;
    @Autowired
    public SubjectRepository subjectRepository;
    @Autowired
    public CourseRepository courseRepository;
    @Autowired
    public CourseService courseService;
    @Autowired
    public SubjectService subjectService;
    @Autowired
    public UserRepository userRepository;
    @GetMapping(value = "/courses")
    public List<Course> getAllCourses() {
        return courseService.getALL();
    }

    @GetMapping(value = "/courses/{id}")
    public Course getCourseById(@PathVariable("id") Long courseId) {
        return courseService.getById(courseId);
    }


    @GetMapping(value = "/courses2/{id}")
    public Course getCourse2(@PathVariable("id") Long courseId) {
        Course course = courseService.getById(courseId);
        User user = userRepository.findUserById(course.getAuthorOfCourse());
        return course;
    }

    @GetMapping(value = "/courses/by-subject-id/{id}")
    public List<Course> getCoursesBySubjectId(@PathVariable("id") Long subjectId) {
        return courseService.getBySubjectId(subjectId);
    }

   //     @PreAuthorize("hasAuthority('USER')")
   @PostMapping(value = "/courses/create-course")
    public String createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        Subject ss = subjectRepository.findByName(courseDTO.getSubject().getName());
        User user = userRepository.findUserById(courseDTO.getAuthorOfCourse());
        Long userId = user.getId();
        if (ss == null) {
            Subject s1 = subjectService.createSubject(new SubjectDTO(courseDTO.getSubject().getName()));
            Course newCourse = new Course(courseDTO.getName(), courseDTO.getDescription(), s1, userId);
            courseRepository.save(newCourse);
        } else {
            Course newCourse = new Course(courseDTO.getName(), courseDTO.getDescription(), ss, userId);
            courseRepository.save(newCourse);
            return "";
        }
        return "";
    }

    @GetMapping(value = "/courses/by-name/{name}")
    public List<Course> getCoursesByName(@PathVariable("name") String name) {
        return courseService.getCoursesByName(name);
    }

    @GetMapping(value = "/courses/courses-by-user-id/{id}")
    public List<Course> getAllCoursesByUserId(@PathVariable("id") Long id) {
        return courseService.getAllByUserId(id);
    }

}
