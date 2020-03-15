package ru.nc.portal.service;

import ru.nc.portal.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestionsForCourse(Long course_id);

    Question createQuestionForCourse(Long course_id, Question question);

    void updateQuestion(Long course_id, Question question);

    void deleteById(Long question_id);

    Question getQuestionById(Long question_id);

    List<Question> createTest(Long course_id, List<Question> questions);
}
