package ru.nc.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nc.portal.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT c.* FROM courses c " +
            "inner join subjects s on s.id=c.subject_id " +
            "WHERE s.id = :subjectId",
    nativeQuery = true)
    public List<Course> findAllBySubjectId(@Param("subjectId") Long subjectId);

    List<Course> getAllByAuthorOfCourse (Long id);

    @Query(value = "" +
            "select c.* " +
            "from courses c " +
            "where lower(c.name) like lower('%'||:name||'%') or " +
            "lower(c.description) like lower('%'||:name||'%')",
    nativeQuery = true)
    public List<Course> findByName(@Param("name") String name);
}
