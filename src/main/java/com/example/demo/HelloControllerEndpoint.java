package com.example.demo;

import org.springframework.web.bind.annotation.*;

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

}

