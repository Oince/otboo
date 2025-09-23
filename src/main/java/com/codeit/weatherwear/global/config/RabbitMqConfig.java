package com.codeit.weatherwear.global.config;

import com.codeit.weatherwear.global.properties.RabbitMqProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

  private final RabbitMqProperties rabbitMqProperties;

  // exchanges
  @Bean
  public TopicExchange notificationExchange() {
    return new TopicExchange(rabbitMqProperties.exchanges().notification());
  }

  @Bean
  public TopicExchange dmExchange() {
    return new TopicExchange(rabbitMqProperties.exchanges().dm());
  }

  @Bean
  public FanoutExchange sseFanoutExchange() {
    return new FanoutExchange(rabbitMqProperties.exchanges().sseFanout());
  }

  // queues
  @Bean
  Queue notificationQueue() {
    return new Queue(rabbitMqProperties.queues().notification(), true);
  }

  @Bean
  public Queue dmQueue() {
    return new Queue(rabbitMqProperties.queues().dm(), true);
  }

  //binding
  @Bean
  public Binding notificationBinding(Queue notificationQueue, TopicExchange notificationExchange) {
    return BindingBuilder
        .bind(notificationQueue)
        .to(notificationExchange)
        .with(rabbitMqProperties.routingKeys().notification());
  }

  @Bean
  public Binding dmBinding(Queue dmQueue, TopicExchange dmExchange) {
    return BindingBuilder
        .bind(dmQueue)
        .to(dmExchange)
        .with(rabbitMqProperties.routingKeys().dm());
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }
}
