package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.OrdersRepository;
import com.kate.yarnshop.entity.*;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import com.kate.yarnshop.validation.SameCustomerValidation;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.kate.yarnshop.constants.Constants.*;

@RestController
@RequestMapping(ORDERS_PATH)
public class OrdersController {
    private final OrdersRepository ordersRepository;
    private final SameCustomerValidation sameCustomerValidation;

    public OrdersController(OrdersRepository ordersRepository, SameCustomerValidation sameCustomerValidation) {
        this.ordersRepository = ordersRepository;
        this.sameCustomerValidation = sameCustomerValidation;
    }

    @GetMapping
    @Secured({ROLE_SELLER})
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("{id}")
    @Secured({ROLE_SELLER, ROLE_CUSTOMER})
    public Order findOrderById(@PathVariable Long id) throws EntityNotFoundException {
        Order order = ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("order"));
        if (sameCustomerValidation.isWrongUser(order.getUser())) {
            throw new UnsupportedOperationException();
        }

        return order;
    }

    @GetMapping("/user/{userId}")
    @Secured({ROLE_SELLER, ROLE_CUSTOMER})
    public List<Order> findOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = ordersRepository.getOrdersByUserId(userId);
        if (sameCustomerValidation.isWrongUser(orders.stream()
                .findFirst()
                .map(Order::getUser)
                .orElseThrow(UnsupportedOperationException::new))) {
            throw new UnsupportedOperationException();
        }
        return orders;

    }

    @PostMapping
    @Secured(ROLE_CUSTOMER)
    public Order createOrder(@RequestBody OrderDto orderDto) {

        ModelMapper modelMapper = new ModelMapper();
        Order order = modelMapper.map(orderDto, Order.class);
        order.setId(null);
        order.setOrderStatus(OrderStatus.PROCESSING);
        for (OrderData data : order.getOrderData()) {
            data.setOrder(order);
        }
        return ordersRepository.save(order);
    }

    @PutMapping("/{id}/{statusId}")
    @Secured({ROLE_SELLER})
    public Order updateOrderStatusById(@PathVariable Long id, @PathVariable Long statusId) throws EntityNotFoundException {
        Order order =
                ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("order"));
        order.setOrderStatus(OrderStatus.getInstance(statusId));
        if (STATUS_CANCELLED.equalsIgnoreCase(order.getOrderStatus().getName())) {
            throw new UnsupportedOperationException();
        } else {
            return ordersRepository.save(order);
        }
    }

    @DeleteMapping("/{id}")
    @Secured(ROLE_SELLER)
    public void deleteOrder(@PathVariable Long id) throws EntityNotFoundException {
        Order order = ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("order"));
        if (STATUS_DONE.equalsIgnoreCase(order.getOrderStatus().getName())) {
            throw new UnsupportedOperationException();
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);
            ordersRepository.save(order);
        }

    }
}
