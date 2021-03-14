package com.kate.yarnshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import static com.kate.yarnshop.constants.Constants.*;

@Data
@Entity
@Table(name = ORDERED_PRODUCT_AND_QUANTITY_TABLE)
public class OrderData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = LINE_ID_ROW, unique = true, nullable = false)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ORDER_ID_ROW, nullable = false)
    private Order order;
    @JoinColumn(name = PRODUCT_ID_ROW)
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    private Product product;
    @Column(name = QUANTITY_ROW, nullable = false)
    private Integer quantity;
}
