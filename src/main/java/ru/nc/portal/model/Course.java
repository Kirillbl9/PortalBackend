package ru.nc.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @SequenceGenerator(name = "courseSeq", sequenceName = "COURSE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseSeq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Lesson> lessons;

    @Column(name = "AUTHOR_OF_COURSE_ID")
  /*  @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_OF_COURSE_ID")
    @JsonIgnore*/
    private Long authorOfCourse;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Question> questions;

    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Course(String name, String description, Subject subject) {
        this.name = name;
        this.description = description;
        this.subject = subject;
    }

    public Course(String name, String description, Subject subject, Long authorOfCourse) {
        this.name = name;
        this.description = description;
        this.subject = subject;
        this.authorOfCourse = authorOfCourse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Long getAuthorOfCourse() {
        return authorOfCourse;
    }

    public void setAuthorOfCourse(Long authorOfCourse) {
        this.authorOfCourse = authorOfCourse;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
