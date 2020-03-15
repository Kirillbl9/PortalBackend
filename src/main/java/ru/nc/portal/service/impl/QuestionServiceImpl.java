package ru.nc.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nc.portal.model.Answer;
import ru.nc.portal.model.Course;
import ru.nc.portal.model.Question;
import ru.nc.portal.repository.AnswerRepository;
import ru.nc.portal.repository.CourseRepository;
import ru.nc.portal.repository.QuestionRepository;
import ru.nc.portal.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Question> getAllQuestionsForCourse(Long course_id) {
        return questionRepository.findAllByCourse_Id(course_id);
    }

    @Override
    public Question createQuestionForCourse(Long course_id, Question question) {
        question.setCourse(courseRepository.findById(course_id).orElse(null));
        return questionRepository.save(question);
    }

    @Override
    public void updateQuestion(Long course_id, Question question) {
        Question existingQuestion = questionRepository.findById(question.getId()).orElse(null);
        if (existingQuestion != null){
            existingQuestion.setContent(question.getContent());
            questionRepository.save(existingQuestion);
        }
    }

    @Override
    public void deleteById(Long question_id) {
        questionRepository.deleteById(question_id);
    }

    @Override
    public Question getQuestionById(Long question_id) {
        return questionRepository.findById(question_id).orElse(null);
    }

    @Override
    public List<Question> createTest(Long course_id, List<Question> questions) {
        Optional<Course> course = courseRepository.findById(course_id);
        List<Answer> answers = new ArrayList<>();
        if (course.isPresent()){
            for(Question question: questions){
                question.getAnswers().forEach(x -> x.setQuestion(question));
                answers.addAll(question.getAnswers());
            }
            questions.forEach(x -> x.setCourse(course.get()));
            questionRepository.saveAll(questions);
            answerRepository.saveAll(answers);
        }
        return null;
    }

}
