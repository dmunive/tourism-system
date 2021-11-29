package com.tourism.config;

import com.tourism.service.CheckInSubscriberService;
import com.tourism.util.CheckInRabbitMqProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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
    TopicExchange exchange() {
        return new TopicExchange(checkInRabbitMqProperties.getExchange());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(checkInRabbitMqProperties.getRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(checkInRabbitMqProperties.getQueue());
        listenerAdapter.setMessageConverter(jsonMessageConverter());
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(CheckInSubscriberService checkInSubscriberService) {
        return new MessageListenerAdapter(checkInSubscriberService, "receiveMessage");
    }
}
