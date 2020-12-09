package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void testGetVolume() throws Exception {
        this.mvc.perform(get("/math/volume/2/3/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 2x3x5 rectangle is 30"));
    }

    @Test
    public void testPOSTVolume() throws Exception {
        this.mvc.perform(post("/math/volume/2/3/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 2x3x5 rectangle is 30"));
    }

    @Test
    public void testPATCHVolume() throws Exception {
        this.mvc.perform(patch("/math/volume/2/3/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 2x3x5 rectangle is 30"));
    }

    @Test
    public void testPUTVolume() throws Exception {
        this.mvc.perform(put("/math/volume/2/3/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 2x3x5 rectangle is 30"));
    }

    @Test
    public void testPostCircleArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "4");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.265482"));
    }

    @Test
    public void testPostRectangleArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("x", "4")
                .param("y", "7");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

    @Test
    public void testPostInvalidArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("x", "4")
                .param("y", "7");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }


}
