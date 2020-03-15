package ru.nc.portal.service;

import ru.nc.portal.model.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getAllLessonsForCourse(Long course_id);
    Lesson getLessonById(Long lesson_id);
    Lesson createLessonForCourse(Long course_id, Lesson lesson);
    Lesson updateLessonForCourse(Long course_id, Lesson lesson);
    void deleteLessonById(Long lesson_id);
}
