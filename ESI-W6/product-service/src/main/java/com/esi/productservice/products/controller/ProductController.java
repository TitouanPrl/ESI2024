package com.esi.productservice.products.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.productservice.products.dto.ProductDto;
import com.esi.productservice.products.dto.ProductQuantityDto;
import com.esi.productservice.products.service.ProductService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/products")
  public List<ProductDto> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/products/{id}")
  public Optional<ProductDto> getProduct(@PathVariable String id) {
    return productService.getProduct(id);
  }

  @GetMapping("/products/quantity/{id}")
  public Optional<ProductQuantityDto> getProductWithQuantity(@PathVariable String id) {
    return productService.getProductWithQuantity(id);
  }

  // -------------------------------- Circuit Breaker

  @GetMapping("/products/quantityCB/{id}")
  @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackQuantityCB")
  public Optional<ProductQuantityDto> getProductWithQuantityCB(@PathVariable String id) {
    return productService.getProductWithQuantityCB(id);
  }

  public Optional<ProductQuantityDto> fallbackQuantityCB(Exception e) {
    log.info("We are not able to fetch the quality of the product");
    return Optional.of(new ProductQuantityDto("00", "p-000-00", "type of product", "description of product",
        BigDecimal.valueOf(0), 0));
  }

  // -------------------------------- Circuit Breaker

  // -------------------------------- Circuit Breaker + TimeLimiter
  // To be comminted out

  /*
   * @GetMapping("/products/quantityCB/{id}")
   *
   * @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackQuantityCB")
   *
   * @TimeLimiter(name = "inventory")
   * public CompletableFuture<Optional<ProductQuantityDto>>
   * getProductWithQuantityCB(@PathVariable String id){
   * System.out.println(new Date() + " Call processing finished = " +
   * Thread.currentThread().getName());
   * return CompletableFuture.supplyAsync(() ->
   * productService.getProductWithQuantityCB(id));
   * }
   * // A CompltableFuture is used for asynchronous programming.
   * // It runs a task on a separate thread than the main application thread and
   * notifies the main thread about its progress, completion or failure
   *
   * public CompletableFuture<Optional<ProductQuantityDto>>
   * fallbackQuantityCB(Exception e) { //RuntimeException runtimeException
   * log.info("We are not able to fetch the quality of the product");
   * return CompletableFuture.supplyAsync(() -> Optional.of(new
   * ProductQuantityDto("00", "p-000-00", "type of product",
   * "description of product", BigDecimal.valueOf(0), 0 )));
   * }
   */
  // -------------------------------- Circuit Breaker + TimeLimiter

  // -------------------------------- Retry
  /*
   * private int attempt = 1;
   *
   * @GetMapping("/products/quantityCB/{id}")
   *
   * @Retry(name = "inventory", fallbackMethod = "fallbackQuantityCB")
   * public CompletableFuture<Optional<ProductQuantityDto>>
   * getProductWithQuantityCB(@PathVariable String id){
   * System.out.println("Retry attempt " + attempt++ + " at " + new Date());
   * return CompletableFuture.supplyAsync(() ->
   * productService.getProductWithQuantityCB(id));
   * }
   *
   * public CompletableFuture<Optional<ProductQuantityDto>>
   * fallbackQuantityCB(Exception e) { //RuntimeException runtimeException
   * log.info("We are not able to fetch the quality of the product");
   * return CompletableFuture.supplyAsync(() -> Optional.of(new
   * ProductQuantityDto("00", "p-000-00", "type of product",
   * "description of product", BigDecimal.valueOf(0), 0 )));
   * }
   */
  // -------------------------------- Retry

  // -------------------------------- RateLimiter

  /*
   * private int requestNumber = 1;
   *
   * @GetMapping("/products/quantityCB/{id}")
   *
   * @RateLimiter(name= "inventory", fallbackMethod = "fallbackRateLimiter")
   * public CompletableFuture<Optional<ProductQuantityDto>>
   * getProductWithQuantityCB(@PathVariable String id){
   * System.out.println("request " + requestNumber++ + " at " + new Date());
   * return CompletableFuture.supplyAsync(() ->
   * productService.getProductWithQuantityCB(id));
   * }
   *
   * public CompletableFuture<Optional<ProductQuantityDto>>
   * fallbackRateLimiter(Exception e) { //RuntimeException runtimeException
   * log.info("We are not able to send new requests");
   * return CompletableFuture.supplyAsync(() -> Optional.of(new
   * ProductQuantityDto("00", "p-000-00", "type of product",
   * "description of product", BigDecimal.valueOf(0), 0 )));
   * }
   */
  // -------------------------------- RateLimiter

  @PostMapping("/products")
  public void addProduct(@RequestBody ProductDto productDto) {
    productService.addProduct(productDto);
  }

  @PutMapping("/products/{id}")
  public void updateProduct(@RequestBody ProductDto productDto, @PathVariable String id) {
    productService.updateProduct(id, productDto);
  }

  @DeleteMapping("/products/{id}")
  public void deleteProduct(@PathVariable String id) {
    productService.deleteProduct(id);
  }
}
