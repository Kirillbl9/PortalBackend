package ru.nc.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pages")
public class Page {
    @Id
    @SequenceGenerator(name = "pageSeq", sequenceName = "PAGE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pageSeq")
    private Long id;
    @Column(name="num")
    private Integer number;
    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;

    public Page() {
    }

    public Page(Integer number, String name, String content) {
        this.number = number;
        this.name = name;
        this.content = content;
    }

    public Page(Integer number, String name, String content, Lesson lesson) {
        this.number = number;
        this.name = name;
        this.content = content;
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
