package com.tourism.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail.message")
@Data
public class MailMessageProperties {
    private String from;
    private String errorSubject;
    private String errorBody;
    private String successSubject;
    private String successBody;
}
