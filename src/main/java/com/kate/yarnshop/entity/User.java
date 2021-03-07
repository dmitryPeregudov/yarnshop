package com.kate.yarnshop.entity;

import com.kate.yarnshop.encription.AttributeEncryptor;
import lombok.Data;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = USER_TABLE)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;
    @Column(name = LOGIN_ROW, unique = true, nullable = false)
    private String login;
    @Column(name = PASSWORD_ROW, nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String password;
    @Column(name = NAME_ROW)
    private String name;
    @Column(name = MIDDLE_NAME_ROW)
    private String middleName;
    @Column(name = SUR_NAME_ROW)
    private String surName;
    @Column(name = ADDRESS_ROW)
    private String address;
    @Column(name = EMAIL_ROW)
    private String email;
    @Column(name = POST_ROW)
    private String post;
    @Column(name = DATE_OF_BIRTH_ROW)
    private String dateOfBirth;
    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = ROLE_ROW)
    private Role role;
}
