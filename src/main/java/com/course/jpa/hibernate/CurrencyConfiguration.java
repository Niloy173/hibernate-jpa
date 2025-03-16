package com.course.jpa.hibernate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "currency-configuration")
@Component
@Data
public class CurrencyConfiguration {
    private String url;
    private String key;
    private String method;
}
