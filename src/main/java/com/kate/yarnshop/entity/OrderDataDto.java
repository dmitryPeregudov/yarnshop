package com.kate.yarnshop.entity;

import lombok.Data;

@Data
public class OrderDataDto {
    private Long id;
    private Order order;
    private Product product;
    private Integer quantity;
}
