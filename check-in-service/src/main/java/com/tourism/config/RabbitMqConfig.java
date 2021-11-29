package com.tourism.config;

import com.tourism.util.CheckInRabbitMqProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConfig {

    private final CheckInRabbitMqProperties checkInRabbitMqProperties;

    public RabbitMqConfig(CheckInRabbitMqProperties rabbitMqProperties) {
        this.checkInRabbitMqProperties = rabbitMqProperties;
    }

    @Bean
    Queue queue() {
        return new Queue(checkInRabbitMqProperties.getQueue(), false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(checkInRabbitMqProperties.getExchange());
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(checkInRabbitMqProperties.getRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate checkInRabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
