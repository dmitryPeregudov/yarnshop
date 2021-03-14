package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.OrdersRepository;
import com.kate.yarnshop.entity.*;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.kate.yarnshop.constants.Constants.*;

//todo set authorities, create collection for del and update status methods
@RestController
@RequestMapping(ORDERS_PATH)
public class OrdersController {
    private final OrdersRepository ordersRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping
    //@Secured(ROLE_ADMIN)
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("{id}")
    public Order findOrderById(@PathVariable Long id) throws EntityNotFoundException {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("order"));
    }

    @GetMapping("/user/{userId}")
    public List<Order> findOrdersByUser(@PathVariable Long userId) {
        return ordersRepository.getOrdersByUserId(userId);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order.setOrderStatus(OrderStatus.PROCESSING);
        return ordersRepository.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Long id) throws EntityNotFoundException {
        return ordersRepository.findById(id).map(existingOrder -> {
            existingOrder.setOrderData(order.getOrderData());
            return ordersRepository.save(existingOrder);
        }).orElseThrow(() -> new EntityNotFoundException("order"));
    }

    @PutMapping("/{id}/{statusId}")
    public Order updateOrderStatusById(@PathVariable Long id, @PathVariable Long statusId) throws EntityNotFoundException {
        Order order =
                ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("order"));
        order.setOrderStatus(OrderStatus.getInstance(statusId));
        return ordersRepository.save(order);
    }

    @DeleteMapping("/{id}")
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
