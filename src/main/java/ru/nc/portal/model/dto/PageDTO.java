package ru.nc.portal.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageDTO {
    @JsonProperty(value = "page_id")
    private Long id;
    @JsonProperty(value = "number")
    private Integer number;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "content")
    private String content;

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
}
