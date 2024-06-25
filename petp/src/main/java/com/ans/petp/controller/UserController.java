package com.ans.petp.controller;

import com.ans.petp.entity.JournalEntry;
import com.ans.petp.entity.User;
import com.ans.petp.repository.UserRepository;
import com.ans.petp.service.JournalEntryService;
import com.ans.petp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping
//    public ResponseEntity<?> getAllUsers()
//    {
//        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
//    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);

        userInDb.setUsername(newUser.getUsername());
        userInDb.setPassword(newUser.getPassword());
        userService.saveUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepository.deleteByUsername(username);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
