package com.demo.lesson02.service;

import org.springframework.stereotype.Service;

@Service
public class MyGreetingService {
    public String greet() {
        return "<h1> Hello from the services! </h1>";
    }
}
