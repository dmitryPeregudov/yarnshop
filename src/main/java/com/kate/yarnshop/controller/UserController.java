package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.UserRepository;
import com.kate.yarnshop.entity.User;
import com.kate.yarnshop.entity.UserPassword;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.kate.yarnshop.constants.Constants.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @Secured(ROLE_ADMIN)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Secured({ROLE_ADMIN, ROLE_SELLER})
    public User findUserById(@PathVariable Long id) throws EntityNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER));
        String authority = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority).orElse("");
        if ((ROLE_SELLER.equalsIgnoreCase(authority) && !user.getRole().getName().equals(CUSTOMER_ROLE))
                || "".equalsIgnoreCase(authority)) {
            throw new EntityNotFoundException(USER);
        }
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) throws EntityNotFoundException {
        String userLogin = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .filter(e -> e instanceof UserDetails)
                .map(e -> ((UserDetails) e).getUsername())
                .orElseThrow(UnsupportedOperationException::new);
        return userRepository.findById(id).map(user -> {
            if (!user.getLogin().equals(userLogin)) {
                throw new UnsupportedOperationException();
            }
            user.setName(newUser.getName());
            user.setMiddleName(newUser.getMiddleName());
            user.setSurName(newUser.getSurName());
            user.setAddress(newUser.getAddress());
            user.setPost(newUser.getPost());
            user.setEmail(newUser.getEmail());
            user.setDateOfBirth(newUser.getDateOfBirth());
            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException(USER));
    }

    @PutMapping({"passwordUpdate/{id}"})
    public User updateUserPassword(@PathVariable Long id, @RequestBody UserPassword password) throws EntityNotFoundException {
        String userLogin = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .filter(e -> e instanceof UserDetails)
                .map(e -> ((UserDetails) e).getUsername())
                .orElseThrow(UnsupportedOperationException::new);
        return userRepository.findById(id).map(existingUser -> {
            if (!existingUser.getLogin().equals(userLogin)) {
                throw new UnsupportedOperationException();
            }
            existingUser.setPassword(password.getPassword());
            return userRepository.saveAndFlush(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException(USER));
    }

    @DeleteMapping("/{id}")
    @Secured(ROLE_ADMIN)
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
