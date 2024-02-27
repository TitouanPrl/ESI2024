package com.esi.w3crud2.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esi.w3crud2.products.dto.ProductDto;
import com.esi.w3crud2.products.model.Product;
import com.esi.w3crud2.products.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts() {

        /*
         * List<Product> products = new ArrayList<>();
         * productRepository.findAll().forEach(products::add);
         * return products;
         */

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products.stream().map(this::mapToProductDto).toList();
    }

    private ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public Optional<ProductDto> getProduct(String id) {
        // return productRepository.findById(id);
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::mapToProductDto);
    }

    public void addProduct(ProductDto productDto) {
        // productRepository.save(product);
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is added to the Database", product.getId());
    }

    public void updateProduct(String id, ProductDto productDto) {
        // productRepository.save(product);
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is updated", product.getId());
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
        log.info("A Product has been deleted");
    }
};
