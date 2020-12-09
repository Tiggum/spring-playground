package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

        Passenger passenger = new Passenger();
        passenger.setFirstName("caden");
        passenger.setLastName("reynolds");

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(100);

        Ticket[] tickets = new Ticket[]{ticket};
        flight.setTickets(tickets);

        return flight;
    }

    @GetMapping("/flights")
    public Flight[] flights() {
        Flight flight1 = new Flight();
        flight1.setDate(new Date());

        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("caden");
        passenger1.setLastName("reynolds");

        Ticket ticket1 = new Ticket();
        ticket1.setPassenger(passenger1);
        ticket1.setPrice(100);

        Ticket[] tickets1 = new Ticket[]{ticket1};
        flight1.setTickets(tickets1);

        Flight flight2 = new Flight();
        flight2.setDate(new Date());

        Passenger passenger2 = new Passenger();
        passenger2.setFirstName("Taylor");
        passenger2.setLastName("reynolds");

        Ticket ticket2 = new Ticket();
        ticket2.setPassenger(passenger2);
        ticket2.setPrice(100);

        Ticket[] tickets2 = new Ticket[]{ticket2};
        flight2.setTickets(tickets2);

        return new Flight[]{flight1, flight2};


    }

    public static class Flight{
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
        private Date date;
        private Ticket[] tickets;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Ticket[] getTickets() {
            return tickets;
        }

        public void setTickets(Ticket[] tickets) {
            this.tickets = tickets;
        }
    }

    public static class Passenger{
        private String firstName;
        private String lastName;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }

    public static class Ticket{
        private Passenger passenger;
        private int price;

        public Passenger getPassenger() {
            return passenger;
        }

        public void setPassenger(Passenger passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

    }

}

