package com.kate.yarnshop.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Data
@Table(name = ORDERS_TABLE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, unique = true)
    private Long id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = USER_ROW)
    private User user;
    @OneToMany(targetEntity = OrderData.class, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderData> orderData = new ArrayList<>();
    @ManyToOne(targetEntity = OrderStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = STATUS_ROW)
    private OrderStatus orderStatus;
}
