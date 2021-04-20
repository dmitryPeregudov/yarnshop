package com.kate.yarnshop.entity;

import lombok.Data;

@Data
public class UserPassword {
    private String oldPassword;
    private String newPassword;
}
