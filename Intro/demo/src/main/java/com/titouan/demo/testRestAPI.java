package com.titouan.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testRestAPI {
    @GetMapping("/esi")
    public String helloWorld() {
        return "Welcome to the ESI course";
    }
    
}
