package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
public class HelloControllerEndpoint {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/math/pi")
    public String pi(){
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String calculate(@RequestParam(defaultValue = "add") String operation, @RequestParam int x, @RequestParam int y){
        return "" + MathService.calculate(operation, x, y);
    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam Integer[] n){
        return MathService.sum(n).toString();
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String volume(@PathVariable int length, @PathVariable int width, @PathVariable int height){
        int volume = MathService.volume(length, width, height);
        String volumeString = String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, volume);
        return volumeString;
    }

    @PostMapping("/math/area")
    public String area(@RequestParam Map<String, String> params){
        String areaString = "Invalid";

        if (params.get("type") == "circle" && params.get("radius") != null){
            Double area = MathService.circleArea(parseInt(params.get("radius")));
            return String.format("Area of a circle with a radius of %d is %f", parseInt(params.get("radius")), area);
        } else if (params.get("type") == "rectangle" && params.get("x") != null && params.get("y") != null) {
            Integer area = MathService.rectangleArea(parseInt(params.get("x")), parseInt(params.get("y")));
            return String.format("Area of a %dx%d rectangle is %d", parseInt(params.get("x")), parseInt(params.get("y")), area);
        }

        return areaString;
    }

    @GetMapping("/flights/flight")
    public Flight flight(){
        Flight flight = new Flight();
        flight.setDate(new Date());

        Flight.Ticket.Passenger passenger = new Flight.Ticket.Passenger();
        passenger.setFirstName("caden");
        passenger.setLastName("reynolds");

        Flight.Ticket ticket = new Flight.Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(100);

        Flight.Ticket[] tickets = new Flight.Ticket[]{ticket};
        flight.setTickets(tickets);

        return flight;
    }

    @GetMapping("/flights")
    public Flight[] flights() {
        Flight flight1 = new Flight();
        flight1.setDate(new Date());

        Flight.Ticket.Passenger passenger1 = new Flight.Ticket.Passenger();
        passenger1.setFirstName("caden");
        passenger1.setLastName("reynolds");

        Flight.Ticket ticket1 = new Flight.Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(100);

        Flight.Ticket[] tickets1 = new Flight.Ticket[]{ticket1};
        flight1.setTickets(tickets1);

        Flight flight2 = new Flight();
        flight2.setDate(new Date());

        Flight.Ticket.Passenger passenger2 = new Flight.Ticket.Passenger();
        passenger2.setFirstName("Taylor");
        passenger2.setLastName("reynolds");

        Flight.Ticket ticket2 = new Flight.Ticket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(250);

        Flight.Ticket[] tickets2 = new Flight.Ticket[]{ticket2};
        flight2.setTickets(tickets2);

        return new Flight[]{flight1, flight2};


    }

    public static class Flight{
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
        private Date date;
        private Ticket[] tickets;
        @JsonProperty("date")
        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
        @JsonProperty("tickets")
        public Ticket[] getTickets() {
            return tickets;
        }

        public void setTickets(Ticket[] tickets) {
            this.tickets = tickets;
        }

        public static class Ticket{
            private Passenger passenger;
            private int price;
            @JsonProperty("passenger")
            public Passenger getPassenger() {
                return passenger;
            }

            public void setPassenger(Passenger passenger) {
                this.passenger = passenger;
            }
            @JsonProperty("price")
            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public static class Passenger{
                private String firstName;
                private String lastName;

                @JsonProperty("lastName")
                public String getLastName() {
                    return lastName;
                }

                public void setLastName(String lastName) {
                    this.lastName = lastName;
                }
                @JsonProperty("firstName")
                public String getFirstName() {
                    return firstName;
                }

                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }
            }

        }
    }

    @PostMapping("/flights/tickets/total")
    public Map<String, Integer> total(@RequestBody Flight flight){
        Flight.Ticket[] tickets = flight.getTickets();

        int sum = 0;

        for (Flight.Ticket ticket : tickets){
            sum+= ticket.getPrice();
        }

        HashMap<String, Integer> total = new HashMap<String, Integer>();
        total.put("result", sum);
        return total;
    }
}

