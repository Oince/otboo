package com.codeit.weatherwear.global.event.listener;

import com.codeit.weatherwear.global.sse.SseMessage;
import com.codeit.weatherwear.global.sse.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
    value = @Queue(value = "", durable = "false", autoDelete = "true", exclusive = "true"),
    exchange = @Exchange(value = "${spring.rabbitmq.properties.exchanges.sse-fanout}",
        type = ExchangeTypes.FANOUT)
))
public class SseRabbitMqListener {

  private final SseService sseService;

  @RabbitHandler
  public void handleSseSendEvent(SseMessage sseMessage) {
    sseService.send(sseMessage);
  }
}
