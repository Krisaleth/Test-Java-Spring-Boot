package com.k23cnt.kntlab04.controller;

import com.k23cnt.kntlab04.dto.UsersDTO;
import com.k23cnt.kntlab04.entity.Users;
import com.k23cnt.kntlab04.service.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers() {
        List<Users> result = usersService.findAll();
        return result;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addUser(@Valid @RequestBody UsersDTO usersDTO) {
        usersService.create(usersDTO);
        return ResponseEntity.ok("User created successfully");
    }
}
