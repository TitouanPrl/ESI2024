package com.esi.w3crud2.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.esi.w3crud2.products.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
