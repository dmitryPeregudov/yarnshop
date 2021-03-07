package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.UserRepository;
import com.kate.yarnshop.entity.User;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kate.yarnshop.constants.Constants.USER;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable Long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, USER));
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) throws EntityNotFoundException {
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setMiddleName(newUser.getMiddleName());
            user.setSurName(newUser.getSurName());
            user.setLogin(newUser.getLogin());
            user.setPassword(newUser.getPassword());
            user.setAddress(newUser.getAddress());
            user.setPost(newUser.getPost());
            user.setEmail(newUser.getEmail());
            user.setDateOfBirth(newUser.getDateOfBirth());
            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException(id, USER));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
