package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.UserRepository;
import com.kate.yarnshop.entity.AuthRequest;
import com.kate.yarnshop.entity.AuthResponse;
import com.kate.yarnshop.entity.Role;
import com.kate.yarnshop.entity.User;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import com.kate.yarnshop.security.JwtProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicUserController {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public BasicUserController(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    @RequestMapping("/register")
    public AuthResponse register(@RequestBody User user) {
        user.setRole(Role.getDefaultRole());
        User savedUser = userRepository.saveAndFlush(user);
        String token = jwtProvider.generateToken(savedUser.getLogin());
        return new AuthResponse(user.getId(), token, savedUser.getRole().getName(),
                savedUser.getName(), savedUser.getSurName(), savedUser.getLogin());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) throws EntityNotFoundException {
        User userEntity = userRepository.getUserByLogin(request.getLogin());
        if (request.getPassword() == null || !request.getPassword().equals(userEntity.getPassword())) {
            throw new EntityNotFoundException("user");
        }
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(userEntity.getId(), token, userEntity.getRole().getName(),
                userEntity.getName(), userEntity.getSurName(), userEntity.getLogin());
    }
}
