package com.kate.yarnshop.entity;

import lombok.Data;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = CONTACTS_TABLE)
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = NAME_ROW, unique = true, nullable = false)
    private String name;
    @Column(name = PHONE_ROW, unique = true, nullable = false)
    private String phone;
    @Column(name = DESCRIPTION_ROW)
    private String desc;
}
