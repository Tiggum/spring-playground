package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloControllerEndpoint.class)
public class YourControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHelloEndPoint() throws Exception {
        this.mvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    public void mathPITest() throws Exception {
        this.mvc.perform(get("/math/pi").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalculateAdd() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void testCalculateSubtract() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("-2"));
    }

    @Test
    public void testCalculateMultiply() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("24"));
    }

    @Test
    public void testCalculateDivide() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("24"));
    }

    @Test
    public void testCalculateNoOperation() throws Exception {
        this.mvc.perform(get("/math/calculate?x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void testPostingSum() throws Exception {
        this.mvc.perform(post("/math/sum?n=2&n=4&n=6&n=8"))
                .andExpect(status().isOk())
                .andExpect(content().string("20"));
    }


}
