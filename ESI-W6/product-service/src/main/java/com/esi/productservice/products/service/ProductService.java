package com.esi.productservice.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.productservice.products.dto.ProductDto;
import com.esi.productservice.products.dto.ProductQuantityDto;
import com.esi.productservice.products.model.Product;
import com.esi.productservice.products.repository.ProductRepository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<ProductDto> getAllProducts() {
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
                .code(product.getCode())
                .build();
    }

    public Optional<ProductDto> getProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::mapToProductDto);
    }

    public Optional<ProductQuantityDto> getProductWithQuantity(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::mapToProductQuantityDto);
    }

    private ProductQuantityDto mapToProductQuantityDto(Product product) {

        Integer quantity = webClientBuilder
                .build()
                .get()
                .uri("http://inventory-service:8083/api/inventory/{code}", product.getCode())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        return ProductQuantityDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .code(product.getCode())
                .quantity(quantity)
                .build();
    }

    // -------------------------------- Circuit Breaker

    public Optional<ProductQuantityDto> getProductWithQuantityCB(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::mapToProductQuantityDto);
    }

    // -------------------------------- Circuit Breaker

    // -------------------------------- All the rest
    /*
     * @SneakyThrows // to handle the exception thrown by Thread.sleep(8000);
     * public Optional<ProductQuantityDto> getProductWithQuantityCB(String id){
     * Optional<Product> product = productRepository.findById(id);
     * Thread.sleep(8000); // just to cause a delay of 8 seconds
     * return product.map(this::mapToProductQuantityDto);
     * }
     */
    // -------------------------------- All the rest

    public void addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .code(productDto.getCode())
                .build();
        productRepository.save(product);
        log.info("Product {} is added to the Database", product.getId());
    }

    public void updateProduct(String id, ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .code(productDto.getCode())
                .build();
        productRepository.save(product);
        log.info("Product {} is updated", product.getId());
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
        log.info("A Product has been deleted");
    }
};
