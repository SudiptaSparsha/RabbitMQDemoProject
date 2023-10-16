package com.selfbetterment.springboot.producer;

import com.selfbetterment.springboot.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void sendJsonMessage(UserDto userDto){
        LOGGER.info(String.format("Json message sent -> %s",userDto.toString()));

        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, userDto);
    }


}
