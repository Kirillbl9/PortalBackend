package ru.nc.portal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nc.portal.model.Question;
import ru.nc.portal.model.dto.QuestionDTO;
import ru.nc.portal.service.QuestionService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class QuestionController {

    @Autowired
    public QuestionService questionService;

    @Autowired
    public ModelMapper modelMapper;

    @GetMapping(value = "/courses/{course_id}/questions")
    public List<Question> getAllQuestionForCourse(@PathVariable("course_id") Long course_id){
        return questionService.getAllQuestionsForCourse(course_id);
    }

    @GetMapping(value = "/questions/{question_id}")
    public Question getQuestionById(@PathVariable("question_id") Long question_id){
        return questionService.getQuestionById(question_id);
    }

    @PostMapping(value = "/courses/{course_id}/questions")
    public ResponseEntity<Question> createQuestion(@PathVariable("course_id") Long course_id, @Valid @RequestBody QuestionDTO questionDTO){
        return new ResponseEntity<>(questionService.createQuestionForCourse(course_id, convertQuestionDTOToEntity(questionDTO)), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/courses/{course_id}/questions")
    public void updateQuestion(@PathVariable("course_id") Long course_id,
                               @Valid @RequestBody QuestionDTO questionDTO){
        questionService.updateQuestion(course_id, convertQuestionDTOToEntity(questionDTO));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/questions/{question_id}")
    public void deleteQuestionById(@PathVariable("question_id") Long question_id)
    {
        questionService.deleteById(question_id);
    }


    @PostMapping(value = "/courses/{course_id}/tests")
    public List<Question> createTest(@PathVariable("course_id") Long course_id,
                     @Valid @RequestBody List<QuestionDTO> questionDTOListDTO){
        // TODO check here author of course
        List<Question> questions = questionDTOListDTO.stream().map(this::convertQuestionDTOToEntity).collect(Collectors.toList());
        return questionService.createTest(course_id, questions);
    }

    private Question convertQuestionDTOToEntity(QuestionDTO questionDTO){
        return modelMapper.map(questionDTO, Question.class);
    }

}
