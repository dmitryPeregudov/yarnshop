package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ProductsRepository;
import com.kate.yarnshop.entity.Product;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kate.yarnshop.constants.Constants.PRODUCT;

@RestController
@RequestMapping("/products")
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
                .orElseThrow(() -> new EntityNotFoundException(id, PRODUCT));
    }

    @GetMapping("/type/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productsRepository.findByType(categoryId);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productsRepository.saveAndFlush(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable final Long id, @RequestBody final Product product) throws EntityNotFoundException {
        return productsRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductType(product.getProductType());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setColor(product.getColor());
                    existingProduct.setPicture(product.getPicture());
                    existingProduct.setWeight(product.getWeight());
                    existingProduct.setThickness(product.getThickness());
                    existingProduct.setYarnType(product.getYarnType());
                    return productsRepository.saveAndFlush(existingProduct);
                })
                .orElseThrow(() -> new EntityNotFoundException(id, PRODUCT));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productsRepository.deleteById(id);
    }
}
