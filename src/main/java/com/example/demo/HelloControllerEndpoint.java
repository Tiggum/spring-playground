package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
