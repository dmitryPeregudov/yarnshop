package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ProductsRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

}
