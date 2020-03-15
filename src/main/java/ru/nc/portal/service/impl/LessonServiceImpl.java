package ru.nc.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nc.portal.model.Course;
import ru.nc.portal.model.Lesson;
import ru.nc.portal.repository.CourseRepository;
import ru.nc.portal.repository.LessonRepository;
import ru.nc.portal.service.LessonService;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Lesson> getAllLessonsForCourse(Long course_id) {
        return lessonRepository.getAllLessonsByCourseIdOrderByNumberAsc(course_id);
    }

    @Override
    public Lesson getLessonById(Long lesson_id) {
        return lessonRepository.findById(lesson_id).orElse(null);
    }

    @Override
    public Lesson createLessonForCourse(Long course_id, Lesson lesson) {
        Course course = courseRepository.findById(course_id).orElse(null);
        List<Lesson> lessons = lessonRepository.getAllByNumberIsGreaterThanEqual(lesson.getNumber());
        for(Lesson x: lessons){
            x.setNumber(x.getNumber()+1);
        }
        lessonRepository.saveAll(lessons);
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson updateLessonForCourse(Long course_id, Lesson lesson) {
        Course course = courseRepository.findById(course_id).orElse(null);
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLessonById(Long lesson_id) {
        Lesson lesson = lessonRepository.findById(lesson_id).orElse(null);
        if (lesson != null){
            List<Lesson> lessons = lessonRepository.getAllByNumberIsGreaterThanEqual(lesson.getNumber());
            for(Lesson x: lessons){
                x.setNumber(x.getNumber()-1);
            }
            lessonRepository.deleteById(lesson_id);
        }
    }
}
