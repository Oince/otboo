package com.codeit.weatherwear.global.event.publisher;

import com.codeit.weatherwear.domain.notification.dto.NotificationDto;
import com.codeit.weatherwear.global.event.dto.MultipleNotificationCreatedEvent;
import com.codeit.weatherwear.global.event.dto.NotificationCreatedEvent;
import com.codeit.weatherwear.global.properties.RabbitMqProperties;
import com.codeit.weatherwear.global.sse.SseMessage;
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
    NotificationDto notificationDto = event.notificationDto();
    SseMessage sseMessage = SseMessage.create(notificationDto.receiverId(), notificationDto);
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().sseSent(), sseMessage);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleMultipleNotificationCreatedEvent(MultipleNotificationCreatedEvent event) {
    event.notificationDtos().stream()
        .map(notificationDto -> SseMessage.create(notificationDto.receiverId(), notificationDto))
        .forEach(sseMessage -> rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
            rabbitMqProperties.queues().sseSent(), sseMessage));
  }

}
