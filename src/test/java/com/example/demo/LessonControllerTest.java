package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        this.mvc.perform(submit)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"I AM TEST\",\"deliveredOn\":\"2020-12-11\"}"));

        this.mvc.perform(get("/lessons/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"I AM TEST\",\"deliveredOn\":\"2020-12-11\"}"));
    }

}
