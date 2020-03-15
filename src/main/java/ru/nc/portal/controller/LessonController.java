package ru.nc.portal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nc.portal.model.Lesson;
import ru.nc.portal.model.dto.LessonDTO;
import ru.nc.portal.service.LessonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class LessonController {

    @Autowired
    public LessonService lessonService;

    @Autowired
    public ModelMapper modelMapper;

    @GetMapping(value = "/courses/{course_id}/lessons")
    public List<Lesson> getAllLessonsForCourse(@PathVariable("course_id") Long course_id){
        return lessonService.getAllLessonsForCourse(course_id);
    }

    @GetMapping(value = "/lessons/{lesson_id}")
    public Lesson getLessonById(@PathVariable("lesson_id") Long lesson_id) {
        return lessonService.getLessonById(lesson_id);
    }

    @PostMapping(value = "/courses/{course_id}/lessons")
    public ResponseEntity<Lesson> createLessonForCourse(@PathVariable("course_id") Long course_id,
                                                        @Valid @RequestBody LessonDTO lessonDTO){
        Lesson lesson = lessonService.createLessonForCourse(course_id, convertLessonDTOToEntity(lessonDTO));
        return new ResponseEntity<>(lesson, HttpStatus.CREATED);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/courses/{course_id}/lessons")
    public void updateLesson(@PathVariable("course_id") Long course_id,
                             @Valid @RequestBody LessonDTO lessonDTO){
        lessonService.updateLessonForCourse(course_id, convertLessonDTOToEntity(lessonDTO));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/lessons/{lesson_id}")
    public void deleteLesson(@PathVariable("lesson_id") Long lesson_id){
        lessonService.deleteLessonById(lesson_id);
    }

    private Lesson convertLessonDTOToEntity(LessonDTO lessonDTO){
        return modelMapper.map(lessonDTO, Lesson.class);
    }
}
