package com.kate.yarnshop.dao;

import com.kate.yarnshop.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypesRepository extends JpaRepository<ProductType, Long> {
}
