package ru.nc.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nc.portal.model.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    public void deleteAllByQuestion_Id(Long question_id);
    public List<Answer> findAllByQuestion_Id(Long question_id);
}
