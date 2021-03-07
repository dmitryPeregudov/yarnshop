package com.kate.yarnshop.dao;

import com.kate.yarnshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Query(value = "select product from Product product where product .productType .id = :type")
    public List<Product> findByType(@Param("type") Long type);
}
