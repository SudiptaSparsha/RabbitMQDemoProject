package com.selfbetterment.springboot.controller;

import com.selfbetterment.springboot.dto.UserDto;
import com.selfbetterment.springboot.producer.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessagae(@RequestParam("message") String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ!");
    }

    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDto userDto){
        rabbitMQProducer.sendJsonMessage(userDto);
        return ResponseEntity.ok("Json message sent to RabbitMQ!");
    }
}
