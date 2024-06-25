package com.ans.petp.service;

import com.ans.petp.entity.User;
import com.ans.petp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveNewUser(User userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER"));
        userRepository.save(userEntry);
    }

    public void saveUser(User userEntry) {
        userRepository.save(userEntry);
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(String.valueOf(id));
    }

    public boolean deleteEntry(ObjectId id) {
        userRepository.deleteById(String.valueOf(id));
        return true;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
