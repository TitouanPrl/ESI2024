package com.esi.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esi.orderservice.model.Order;
import com.esi.orderservice.service.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class ProducerController {

    @Autowired
    private ProducerService producerService;
    
    
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
    producerService.sendMessageToOrderTopic(message);
    return ResponseEntity.ok("Message sent to the order topic");
    } 

    @PostMapping("/publish")
    public ResponseEntity<String> publishjson(@RequestBody Order order){
    producerService.sendJsonToOrderTopic(order);
    return ResponseEntity.ok("Json order object sent to the order topic");
    }

}
