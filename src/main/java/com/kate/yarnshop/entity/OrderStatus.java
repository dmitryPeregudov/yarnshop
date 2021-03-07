package com.kate.yarnshop.entity;

import lombok.Data;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = ORDER_STATUSES_TABLE)
@Data
public class OrderStatus {
    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = NAME_ROW)
    private String name;
}
