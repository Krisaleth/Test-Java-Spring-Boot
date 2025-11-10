package com.demo.lesson02.controller;

import com.demo.lesson02.service.MyGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyGreetingController {
    private final MyGreetingService myGreetingService;

    @Autowired
    public MyGreetingController(MyGreetingService myGreetingService) {
        this.myGreetingService = myGreetingService;
    }

    @GetMapping("/my-greet")
    public String greet() {
        return myGreetingService.greet();
    }
}
