package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ProductsRepository;
import com.kate.yarnshop.entity.Product;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import static com.kate.yarnshop.constants.Constants.*;

@RestController
@RequestMapping(SELLER_PRODUCTS_PATH)
public class SellerProductsController {
    private final ProductsRepository productsRepository;

    public SellerProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
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
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) throws EntityNotFoundException {
        try {
            productsRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(PRODUCT);
        }
    }
}
