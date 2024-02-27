package com.esi.w3crud2.orders;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esi.w3crud2.products.model.Product;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/products/{productId}/orders")
    public List<Order> getAllOrders(@PathVariable String productId) {
        return orderService.getAllOrders(productId);
    }

    @GetMapping("/products/{productId}/orders/{orderId}")
    public Optional<Order> getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping("/products/{productId}/orders")
    public void addOrder(@RequestBody Order order, @PathVariable String productId) {
        order.setProduct(new Product(productId));
        orderService.addOrder(order);
    }

    @PutMapping("/products/{productId}/orders/{orderId}")
    public void updateOrder(@RequestBody Order order, @PathVariable String productId, @PathVariable String orderId) {
        order.setProduct(new Product(productId));
        orderService.updateOrder(order);
    }

    @DeleteMapping("/products/{productId}/orders/{orderId}")
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }
}
