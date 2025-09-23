package com.codeit.weatherwear.global.config.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rabbitmq")
public record RabbitMqConnection(
    String host,
    int port,
    String username,
    String password
) {

}
