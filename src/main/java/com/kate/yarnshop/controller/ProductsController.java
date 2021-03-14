package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ProductsRepository;
import com.kate.yarnshop.entity.Product;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kate.yarnshop.constants.Constants.*;

@RestController
@RequestMapping(PRODUCTS_PATH)
public class ProductsController {
    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws EntityNotFoundException {
        return productsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT));
    }

    @GetMapping("/type/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productsRepository.findByType(categoryId);
    }
}
