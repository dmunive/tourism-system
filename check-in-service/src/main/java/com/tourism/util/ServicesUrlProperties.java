package com.tourism.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "services.url")
@Data
public class ServicesUrlProperties {

    private String reservation;
    private String owner;

}
