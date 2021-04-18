package com.kate.yarnshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
    private String firstName;
    private String lastName;
    private String login;
}
