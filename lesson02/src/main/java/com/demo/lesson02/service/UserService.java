package com.demo.lesson02.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUserDetails() {
        return "<h1>Get user details</h1>";
    }
}
