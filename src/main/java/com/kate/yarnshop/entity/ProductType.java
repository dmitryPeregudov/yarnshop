package com.kate.yarnshop.entity;

import lombok.Data;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = PRODUCT_TYPES_TABLE)
@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, unique = true)
    private Long id;
    @Column(name = NAME_ROW, unique = true, nullable = false)
    private String name;
    @Column(name = INFO_ROW)
    private String info;
}
