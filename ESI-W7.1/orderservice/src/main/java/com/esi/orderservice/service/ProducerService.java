package com.esi.orderservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.orderservice.model.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToOrderTopic(String message){
        log.info("Message send to order topic: {} ", message);
        kafkaTemplate.send("orderTopic", message);
    }

    private final KafkaTemplate<String, Order> jsonKafkaTemplate;
    
    public void sendJsonToOrderTopic(Order order){
        log.info("Log message - Send order json object to order topic: {} ", order.toString());
        jsonKafkaTemplate.send("orderTopicJson", order);
}
}
