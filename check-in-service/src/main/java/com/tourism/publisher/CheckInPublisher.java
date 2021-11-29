package com.tourism.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.domain.message.CheckInPublisherMessage;
import com.tourism.util.CheckInRabbitMqProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckInPublisher {

    private final AmqpTemplate rabbitTemplate;
    private final CheckInRabbitMqProperties rabbitMqProperties;
    private final ObjectMapper objectMapper;

    public CheckInPublisher(AmqpTemplate rabbitTemplate,
                            CheckInRabbitMqProperties rabbitMqProperties,
                            ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqProperties = rabbitMqProperties;
        this.objectMapper = objectMapper;
    }

    public void publish(CheckInPublisherMessage checkIn) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(rabbitMqProperties.getExchange(),
                rabbitMqProperties.getRoutingKey(), objectMapper.writeValueAsString(checkIn));
        log.info("message: {}", checkIn);
    }
}
