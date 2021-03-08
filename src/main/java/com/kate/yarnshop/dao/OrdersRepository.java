package com.kate.yarnshop.dao;

import com.kate.yarnshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select order from Order order where order .user .id = :userId")
    List<Order> getOrdersByUserId(@Param("userId") Long userId);
}
