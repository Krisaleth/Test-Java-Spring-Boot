package com.demo.lesson02.ioc_spring;

import org.springframework.stereotype.Service;

interface GreetingService {
    String greet(String name);
}

@Service
public class GreetingServiceImpl implements GreetingService{
    @Override
    public String greet(String name) {
        return "<h2>Xin chào, " + name + "</h2>";
    }
}
