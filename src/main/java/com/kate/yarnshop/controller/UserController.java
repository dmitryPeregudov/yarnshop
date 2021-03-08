package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.UserRepository;
import com.kate.yarnshop.entity.User;
import com.kate.yarnshop.entity.UserPassword;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kate.yarnshop.constants.Constants.USER;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, USER));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) throws EntityNotFoundException {
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setMiddleName(newUser.getMiddleName());
            user.setSurName(newUser.getSurName());
            user.setAddress(newUser.getAddress());
            user.setPost(newUser.getPost());
            user.setEmail(newUser.getEmail());
            user.setDateOfBirth(newUser.getDateOfBirth());
            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException(id, USER));
    }

    @PutMapping({"passwordUpdate/{id}"})
    public User updateUserPassword(@PathVariable Long id, @RequestBody UserPassword password) throws EntityNotFoundException {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setPassword(password.getPassword());
            return userRepository.saveAndFlush(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException(id, USER));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
