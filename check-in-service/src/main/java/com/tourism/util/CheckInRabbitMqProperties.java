package com.tourism.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "check-in.rabbit-mq")
@Data
public class CheckInRabbitMqProperties {

    private String queue;
    private String exchange;
    private String routingKey;

}
