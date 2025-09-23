package com.codeit.weatherwear.global.sse;

import com.codeit.weatherwear.domain.notification.dto.NotificationDto;
import com.codeit.weatherwear.domain.notification.event.MultipleNotificationCreatedEvent;
import com.codeit.weatherwear.domain.notification.event.NotificationCreatedEvent;
import com.codeit.weatherwear.global.config.rabbitmq.RabbitMqProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class SseRabbitMqPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final RabbitMqProperties rabbitMqProperties;

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleNotificationCreatedEvent(NotificationCreatedEvent event) {
    sendToSseExchange(event.notificationDto());
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleMultipleNotificationCreatedEvent(MultipleNotificationCreatedEvent event) {
    event.notificationDtos().forEach(this::sendToSseExchange);
  }

  private void sendToSseExchange(NotificationDto notificationDto) {
    SseMessage sseMessage = SseMessage.create(notificationDto.receiverId(), notificationDto);
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchanges().sseFanout(), "", sseMessage);
  }

}
