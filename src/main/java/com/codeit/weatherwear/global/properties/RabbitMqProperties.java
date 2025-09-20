package com.codeit.weatherwear.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rabbitmq.properties")
public record RabbitMqProperties(
    String exchange,
    Queues queues,
    RoutingKeys routingKeys
) {

  public record Queues(
      String notifications,
      String dmReceived,
      String sseSent
  ) {}

  public record RoutingKeys(
      String notification,
      String dmReceived,
      String sseSent
  ) {}

}
