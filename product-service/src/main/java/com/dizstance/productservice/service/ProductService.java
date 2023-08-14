package com.dizstance.productservice.service;

import com.dizstance.productservice.dto.ProductRequestDTO;
import com.dizstance.productservice.dto.ProductResponseDTO;
import com.dizstance.productservice.model.Product;
import com.dizstance.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct (ProductRequestDTO productRequestDTO) {
        Product savedProduct = productMapper(productRequestDTO);
        productRepository.save(savedProduct);
        log.info("Product {} is saved", savedProduct.getId());

        //ProductResponseDTO productResponseDTO = productRepository.findById("1").map(productResponseDTOMapper()).orElseThrow();
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productResponseDTOMapper())
                .toList();
    }

    private Product productMapper (ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.name())
                .description(productRequestDTO.description())
                .price(productRequestDTO.price())
                .build();
    }

    private Function<Product, ProductResponseDTO> productResponseDTOMapper() {
        return (product ->
                new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()));
    }


}
