package com.codeit.weatherwear.global.config.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.rabbitmq.properties")
public record RabbitMqProperties(
    Exchanges exchanges,
    Queues queues,
    RoutingKeys routingKeys
) {

  public record Exchanges(
      String notification,
      String dm,
      String sseFanout
  ) {}

  public record Queues(
      String notification
  ) {}

  public record RoutingKeys(
      String notification
  ) {}

}
