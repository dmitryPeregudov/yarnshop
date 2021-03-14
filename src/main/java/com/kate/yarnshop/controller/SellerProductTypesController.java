package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ProductTypesRepository;
import com.kate.yarnshop.entity.ProductType;
import com.kate.yarnshop.exceptions.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import static com.kate.yarnshop.constants.Constants.*;

@RestController
@RequestMapping(SELLER_PRODUCT_TYPES_PATH)
public class SellerProductTypesController {
    private final ProductTypesRepository productTypesRepository;

    public SellerProductTypesController(ProductTypesRepository productTypesRepository) {
        this.productTypesRepository = productTypesRepository;
    }

    @PostMapping
    public ProductType createProductType(@RequestBody ProductType productType) {
        return productTypesRepository.saveAndFlush(productType);
    }

    @PutMapping("/{id}")
    public ProductType updateProductType(@RequestBody ProductType productType, @PathVariable Long id) throws EntityNotFoundException {
        return productTypesRepository.findById(id).map(type -> {
            type.setName(productType.getName());
            type.setInfo(productType.getInfo());
            return productTypesRepository.saveAndFlush(type);
        }).orElseThrow(() -> new EntityNotFoundException(PRODUCT_TYPE));
    }

    @DeleteMapping("/{id}")
    public void deleteProductTypeById(@PathVariable Long id) throws EntityNotFoundException {
        try {
            productTypesRepository.deleteById(id);
        } catch (
                EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(PRODUCT);
        }
    }
}
