package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {

    ObjectMapper map = new ObjectMapper();
    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testPOSTAndGetId() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setDeliveredOn(new Date());
        lesson.setTitle("I AM TEST");

        String json = map.writeValueAsString(lesson);

        MockHttpServletRequestBuilder submit = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        ResultActions result = this.mvc.perform(submit)
                .andExpect(status().isOk());

        System.out.println(result.andReturn().getResponse().getContentAsString());
        Lesson respondedLesson = map.readValue(result.andReturn().getResponse().getContentAsString(), Lesson.class);
        String testString = String.format("{\"id\":%d,\"title\":\"I AM TEST\",\"deliveredOn\":\"2020-12-11\"}", respondedLesson.getId());

        this.mvc.perform(get(String.format("/lessons/%d", respondedLesson.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(testString));
    }

    @Test
    @Transactional
    @Rollback
    public void testLessonPatch() throws Exception{
        Lesson lesson = new Lesson();
        lesson.setDeliveredOn(new Date());
        lesson.setTitle("");

        String json = map.writeValueAsString(lesson);

        MockHttpServletRequestBuilder submit = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        ResultActions result = this.mvc.perform(submit).andExpect(status().isOk());
        Lesson resultLesson = map.readValue(result.andReturn().getResponse().getContentAsString(), Lesson.class);

        lesson.setTitle("Patched Test");
        lesson.setDeliveredOn(new Date());
        json = map.writeValueAsString(lesson);

        MockHttpServletRequestBuilder patch = patch(String.format("/lessons/%d", resultLesson.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);


        this.mvc.perform(patch)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("{\"id\":%d,\"title\":\"Patched Test\",\"deliveredOn\":\"2020-12-11\"}", resultLesson.getId())));

    }

}
