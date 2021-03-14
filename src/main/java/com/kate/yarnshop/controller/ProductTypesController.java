package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ProductTypesRepository;
import com.kate.yarnshop.entity.ProductType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kate.yarnshop.constants.Constants.PRODUCT_TYPES_PATH;

@RestController
@RequestMapping(PRODUCT_TYPES_PATH)
public class ProductTypesController {
    private final ProductTypesRepository productTypesRepository;

    public ProductTypesController(ProductTypesRepository productTypesRepository) {
        this.productTypesRepository = productTypesRepository;
    }

    @GetMapping
    public List<ProductType> getAllProductTypes() {
        return productTypesRepository.findAll();
    }
}
