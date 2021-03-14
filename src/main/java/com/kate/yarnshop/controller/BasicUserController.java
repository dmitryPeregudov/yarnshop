package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.UserRepository;
import com.kate.yarnshop.entity.Role;
import com.kate.yarnshop.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicUserController {

    private final UserRepository userRepository;

    public BasicUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @RequestMapping("/register")
    public User register(@RequestBody User user) {
        user.setRole(Role.getDefaultRole());
        return userRepository.saveAndFlush(user);
    }
}
