package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloControllerEndpoint.class)
public class YourControllerTest {
    ObjectMapper objMapper = new ObjectMapper();
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

    @Test
    public void testPostTicketsTotal() throws Exception{
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\":[{\"passenger\":{\"firstName\":\"Some name\",\"lastName\":\"Some other name\"}," +
                        "\"price\":200},{\"passenger\":{\"firstName\":\"Name B\",\"lastName\":\"Name C\"},\"price\":150}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testPostTicketsTotalAsObjectMapper() throws Exception{
//        HelloControllerEndpoint.Flight.Ticket.Passenger pass1 = new HelloControllerEndpoint.Flight.Ticket.Passenger();
//        pass1.setFirstName("first");
//        pass1.setLastName("last");
//
//        HelloControllerEndpoint.Flight.Ticket ticket1 = new HelloControllerEndpoint.Flight.Ticket();
//        ticket1.setPassenger(pass1);
//        ticket1.setPrice(100);
//        HelloControllerEndpoint.Flight.Ticket ticket2 = new HelloControllerEndpoint.Flight.Ticket();
//        ticket2.setPassenger(pass1);
//        ticket2.setPrice(250);
//        HashMap<String,Object> payload = new HashMap<String,Object>(){{
//            this.put("tickets", Arrays.asList(ticket1, ticket2
//            ));
//
//        }};
//        String json = objMapper.writeValueAsString(payload);
//        this.mvc.perform(
//                post("/flights/tickets/total")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json)
//        )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.result", org.hamcrest.Matchers.is(300)));

        HashMap<String, Object> data = new HashMap<String, Object>(){
            {
              put("tickets", "{\"tickets\":[{\"passenger\":{\"firstName\":\"Some name\",\"lastName\":\"Some other name\"}," +
                      "\"price\":200},{\"passenger\":{\"firstName\":\"Name B\",\"lastName\":\"Name C\"},\"price\":150}]}");
            }
        } ;


        String json = objMapper.writeValueAsString(data);

        System.out.println(json);
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data.toString());

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testPostTicketsTotalAsFileFixture() throws Exception{
        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }


    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }


}
