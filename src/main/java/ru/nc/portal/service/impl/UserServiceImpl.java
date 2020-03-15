package ru.nc.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nc.portal.exception.ResourceNotFoundException;
import ru.nc.portal.model.Course;
import ru.nc.portal.model.User;
import ru.nc.portal.repository.CourseRepository;
import ru.nc.portal.repository.UserRepository;
import ru.nc.portal.security.UserPrincipal;
import ru.nc.portal.service.CourseService;
import ru.nc.portal.service.UserService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;


    @Override
    public User getCurrentUser(UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @Override
    public void setCourseForUser(Long user_id, Long course_id) {
        Course course = courseService.getById(course_id);
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        user.getCourses().add(course);
        course.getUsers().add(user);
        userRepository.save(user);
    }

    @Override
    public Course getCourseByIdForUser(Long user_id, Long course_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        return user.getCourses().stream().filter(course -> course.getId().equals(course_id)).findFirst().orElse(null);
    }

    @Override
    public List<Course> getAllCoursesForUser(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "id", user_id));
        return user.getCourses().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User updateProfileImageForUser(Long user_id, MultipartFile file) throws IOException {
        User user = userRepository.findUserById(user_id);
        user.setImage(file.getBytes());
        return userRepository.save(user);
    }

}
