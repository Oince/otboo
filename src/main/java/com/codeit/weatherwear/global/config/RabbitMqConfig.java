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
  public TopicExchange exchange() {
    return new TopicExchange(rabbitMqProperties.exchanges().notification());
  }

  @Bean
  public FanoutExchange dmFanoutExchange() {
    return new FanoutExchange(rabbitMqProperties.exchanges().dmFanout());
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

  //binding
  @Bean
  public Binding notificationBinding(Queue notificationQueue, TopicExchange exchange) {
    return BindingBuilder
        .bind(notificationQueue)
        .to(exchange)
        .with(rabbitMqProperties.routingKeys().notification());
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }
}
