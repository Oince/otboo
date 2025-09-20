package com.codeit.weatherwear.global.config;

import com.codeit.weatherwear.global.properties.RabbitMqProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

  private final RabbitMqProperties rabbitMqProperties;

  // exchange
  @Bean
  TopicExchange exchange() {
    return new TopicExchange(rabbitMqProperties.exchange());
  }

  // queues
  @Bean
  Queue notificationQueue() {
    return new Queue(rabbitMqProperties.queues().notifications(), true);
  }

  @Bean
  Queue dmReceivedQueue() {
    return new Queue(rabbitMqProperties.queues().dmReceived(), true);
  }

  @Bean
  Queue sseSentQueue() {
    return new Queue(rabbitMqProperties.queues().sseSent(), true);
  }

  //binding
  @Bean
  public Binding notificationBinding(Queue notificationQueue, TopicExchange exchange) {
    return BindingBuilder
        .bind(notificationQueue)
        .to(exchange)
        .with(rabbitMqProperties.routingKeys().notification());
  }

  @Bean
  public Binding dmReceivedBinding(Queue dmReceivedQueue, TopicExchange exchange) {
    return BindingBuilder
        .bind(dmReceivedQueue)
        .to(exchange)
        .with(rabbitMqProperties.routingKeys().dmReceived());
  }

  @Bean
  public Binding sseSentBinding(Queue sseSentQueue, TopicExchange exchange) {
    return BindingBuilder
        .bind(sseSentQueue)
        .to(exchange)
        .with(rabbitMqProperties.routingKeys().sseSent());
  }
}
