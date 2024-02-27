package com.esi.w2crud1.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        // .findAll() returns all entities that are saved to the database
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Optional<Product> getProduct(String id) {
        // findById() method allows us to get or retrieve an entity based on a given id
        // from the DB.
        // It belongs to the CrudRepository interface defined by Spring Data.
        return productRepository.findById(id);
    }

    public void addProduct(Product product) {
        // the save() method allows us to add/update an entity to/in the DB.
        // It also belongs to the CrudRepository interface defined by Spring Data.
        productRepository.save(product);
    }

    // To be solved by students
    public void updateProduct(String id, Product product) {
        if (getProduct(id) != null) {
            productRepository.save(product);
        }
    }

    // To be solved by students
    public void deleteProduct(String id) {
        if (getProduct(id) != null) {
            productRepository.deleteById(id);;
        }    
    }

};
