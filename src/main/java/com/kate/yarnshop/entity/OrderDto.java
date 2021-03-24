package com.kate.yarnshop.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private User user;
    private List<OrderDataDto> orderData = new ArrayList<>();
    private OrderStatus orderStatus;
}
