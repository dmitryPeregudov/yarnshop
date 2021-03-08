package com.kate.yarnshop.entity;

import com.kate.yarnshop.exceptions.EntityNotFoundException;
import lombok.Data;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = ORDER_STATUSES_TABLE)
@Data
public class OrderStatus {

    public static final OrderStatus PROCESSING = new OrderStatus(1L, STATUS_PROCESSING);
    public static final OrderStatus SENT = new OrderStatus(2L, STATUS_SENT);
    public static final OrderStatus DONE = new OrderStatus(3L, STATUS_DONE);

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = NAME_ROW,nullable = false)
    private String name;

    private OrderStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OrderStatus() {
    }

    public static OrderStatus getInstance(Long id) throws EntityNotFoundException {
        switch (id.intValue()) {
            case 1:
                return new OrderStatus(1L, STATUS_PROCESSING);
            case 2:
                return new OrderStatus(2L, STATUS_SENT);
            case 3:
                return new OrderStatus(3L, STATUS_DONE);
            default:
                throw new EntityNotFoundException(id, "order status");
        }
    }
}
