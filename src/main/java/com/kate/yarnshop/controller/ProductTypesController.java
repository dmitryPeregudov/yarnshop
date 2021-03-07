package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ProductTypesRepository;
import com.kate.yarnshop.entity.ProductType;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kate.yarnshop.constants.Constants.PRODUCT_TYPE;

@RestController
public class ProductTypesController {
    private final ProductTypesRepository productTypesRepository;

    public ProductTypesController(ProductTypesRepository productTypesRepository) {
        this.productTypesRepository = productTypesRepository;
    }

    @GetMapping("/productTypes")
    public List<ProductType> getAllProductTypes() {
        return productTypesRepository.findAll();
    }

    @PostMapping("/productTypes")
    public ProductType createProductType(@RequestBody ProductType productType) {
        return productTypesRepository.saveAndFlush(productType);
    }

    @PutMapping("/productTypes/{id}")
    public ProductType updateProductType(@RequestBody ProductType productType, @PathVariable Long id) throws EntityNotFoundException {
        return productTypesRepository.findById(id).map(type -> {
            type.setName(productType.getName());
            type.setInfo(productType.getInfo());
            return productTypesRepository.saveAndFlush(productType);
        }).orElseThrow(() -> new EntityNotFoundException(id, PRODUCT_TYPE));
    }

    @DeleteMapping("/productTypes/{id}")
    public void deleteProductTypeById(@PathVariable Long id) {
        productTypesRepository.deleteById(id);
    }
}
