package com.esi.paymentservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.esi.paymentservice.model.Order;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ConsumerService {


@KafkaListener(topics = "orderTopic", groupId = "orderEventGroup")
    public void consums(String message){
        log.info("Log message - Message recieved from order topic: {} ", message);
} 

    
@KafkaListener(topics = "orderTopicJson", groupId = "orderEventGroup" )
public void consums(Order order){
    log.info("Log message recieved from order topic: {} ", order.toString());
}
}

