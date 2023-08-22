package com.dizstance.productservice.controller;

import com.dizstance.productservice.dto.ProductRequestDTO;
import com.dizstance.productservice.dto.ProductResponseDTO;
import com.dizstance.productservice.model.Product;
import com.dizstance.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        productService.createProduct(productRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }


}
