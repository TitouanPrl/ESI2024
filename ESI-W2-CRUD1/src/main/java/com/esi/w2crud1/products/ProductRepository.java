package com.esi.w2crud1.products;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

    // getAllProducts()
    // getProduct(String Id)
    // addProduct(Product product)
    // updateProduct(String id, Product product)
    // deleteProduct(String id)

}
