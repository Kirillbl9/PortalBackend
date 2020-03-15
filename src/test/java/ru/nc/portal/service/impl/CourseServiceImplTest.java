package ru.nc.portal.service.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nc.portal.model.Course;
import ru.nc.portal.model.dto.CourseDTO;
import ru.nc.portal.repository.CourseRepository;
import ru.nc.portal.service.CourseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "application.test.yaml")
@Sql("/data-h2.sql")
@ActiveProfiles("test")
public class CourseServiceImplTest {

    @TestConfiguration
    static class CourseServiceImplTestContextConfiguration {

        @Bean
        public CourseService courseService() {
            return new CourseServiceImpl();
        }
    }

    @Autowired
    private CourseService courseService;

    @Before
    public void setUp() {
    }
    @Ignore
    @Test
    public void whenGetByName_ListOfCoursesShouldBeFound() {
        String name = "J";
        List<Course> found = courseService.getCoursesByName(name);
        assertThat(found.get(0).getName()).isEqualTo("Java in action");
    }
}