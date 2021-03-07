package com.kate.yarnshop.entity;

import lombok.Data;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = PRODUCTS_TABLE)
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, unique = true)
    private Long id;
    @Column(name = PRODUCT_TYPE_ROW, nullable = false)
    @ManyToOne(targetEntity = ProductType.class)
    private ProductType productType;
    @Column(name = DESCRIPTION_ROW, nullable = false)
    private String description;
    @Column(name = PRICE_ROW, nullable = false)
    private Double price;
    @Column(name = WEIGHT_ROW, nullable = false)
    private Integer weight;
    @Column(name = THICKNESS_ROW, nullable = false)
    private Integer thickness;
    @Column(name = COLOR_ROW, nullable = false)
    private String color;
    @Column(name = PICTURE_ROW, nullable = false)
    private String picture;
}
