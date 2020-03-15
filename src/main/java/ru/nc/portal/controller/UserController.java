package ru.nc.portal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.nc.portal.exception.ResourceNotFoundException;
import ru.nc.portal.model.Course;
import ru.nc.portal.model.User;
import ru.nc.portal.repository.UserRepository;
import ru.nc.portal.security.CurrentUser;
import ru.nc.portal.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.nc.portal.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public ModelMapper modelMapper;

    @GetMapping("/users/by-id/{id}")
    // @PreAuthorize("hasRole('USER')")
    public User getUserById(@PathVariable("id")Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/me")
   // @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getCurrentUser(userPrincipal);
    }

    @PostMapping("/users/me/courses/{course_id}")
    @PreAuthorize("hasRole('USER')")
    public void addCourseForUser(@CurrentUser UserPrincipal userPrincipal, @PathVariable("course_id") Long course_id){
         userService.setCourseForUser(userPrincipal.getId(), course_id);
    }

    @GetMapping("/users/me/courses/{course_id}")
    @PreAuthorize("hasRole('USER')")
    public Course getCourseByIdForUser(@CurrentUser UserPrincipal userPrincipal, @PathVariable("course_id") Long course_id) {
        return userService.getCourseByIdForUser(userPrincipal.getId(), course_id);
    }

    @GetMapping("/users/me/courses")
    @PreAuthorize("hasRole('USER')")
    public List<Course> getAllCoursesForUser(@CurrentUser UserPrincipal userPrincipal){
        return userService.getAllCoursesForUser(userPrincipal.getId());
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping("/users/me")
//    @PreAuthorize("hasRole('USER')")
//    public void updateUser(@CurrentUser UserPrincipal userPrincipal,
//                           @Valid @RequestBody UserDTO userDTO){
//        userService.updateUser(convertUserDTOToEntity(userDTO));
//    }
//
//    private User convertUserDTOToEntity(UserDTO userDTO) {
//        return modelMapper.map(userDTO, User.class);
//    }
    @PatchMapping("/users/me/change-image")
    @PreAuthorize("hasRole('USER')")
    public User updateImageForUser(@RequestParam("profile_image") MultipartFile file,
                                   @CurrentUser UserPrincipal userPrincipal) throws IOException {
        return userService.updateProfileImageForUser(userPrincipal.getId(), file);
    }
}
