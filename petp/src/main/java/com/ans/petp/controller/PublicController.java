package com.ans.petp.controller;

import com.ans.petp.entity.User;
import com.ans.petp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User newUser){
        userService.saveUser(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
