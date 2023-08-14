package com.dizstance.productservice.repository;

import com.dizstance.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ProductRepository extends MongoRepository <Product, String> {

//    @Query(value = "{'name' : ?0}")
//    Optional<Product> findByName(String name);
}
