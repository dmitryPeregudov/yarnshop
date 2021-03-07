package com.kate.yarnshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = ROLE_TABLE)
@Data
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, unique = true, nullable = false)
    private Long id;
    @Column(name = NAME_ROW, nullable = false)
    private String name;

    public static Role getDefaultRole() {
        return new Role(1L, CUSTOMER_ROLE);
    }

    public Role() {
    }
}
