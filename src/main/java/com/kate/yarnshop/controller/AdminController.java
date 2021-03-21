package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.UserRepository;
import com.kate.yarnshop.entity.Role;
import com.kate.yarnshop.entity.User;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.kate.yarnshop.constants.Constants.ROLE_ADMIN;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping({"/createAdministrativeUser"})
    @Secured(ROLE_ADMIN)
    public User createAdministrativeUser(@RequestBody User user) throws EntityNotFoundException {
        if (user.getRole().getId().equals(Role.getDefaultRole().getId())) {
            throw new UnsupportedOperationException();
        }
        user.setRole(Role.getInstance(user.getRole().getId()));
        return userRepository.saveAndFlush(user);
    }
}
