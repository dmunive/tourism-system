package com.tourism.config;

import com.tourism.util.ServicesUrlProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

    private final ServicesUrlProperties servicesUrlProperties;

    private RestTemplateConfig(ServicesUrlProperties servicesUrlProperties) {
        this.servicesUrlProperties = servicesUrlProperties;
    }

    @Bean
    public RestTemplate ownerServiceRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(servicesUrlProperties.getOwner()).build();
    }

    @Bean
    public RestTemplate reservationServiceRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(servicesUrlProperties.getReservation()).build();
    }
}
