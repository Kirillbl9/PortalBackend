package ru.nc.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nc.portal.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
     Subject findByName(String name);
}
