package com.codeit.weatherwear.global.event.publisher;

import com.codeit.weatherwear.global.event.dto.ClothAttributeAddedEvent;
import com.codeit.weatherwear.global.event.dto.ClothAttributeUpdatedEvent;
import com.codeit.weatherwear.global.event.dto.DirectMessageReceivedEvent;
import com.codeit.weatherwear.global.event.dto.DomainEvent;
import com.codeit.weatherwear.global.event.dto.FeedLikeEvent;
import com.codeit.weatherwear.global.event.dto.FolloweeFeedPostedEvent;
import com.codeit.weatherwear.global.event.dto.NewFeedCommentEvent;
import com.codeit.weatherwear.global.event.dto.NewFollowerEvent;
import com.codeit.weatherwear.global.event.dto.RoleChangedEvent;
import com.codeit.weatherwear.global.event.dto.WeatherAlertEvent;
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
public class NotificationRabbitMqPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final RabbitMqProperties rabbitMqProperties;

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleNewFollowerEvent(NewFollowerEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleClothAttributeAddedEvent(ClothAttributeAddedEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleClothAttributeUpdatedEvent(ClothAttributeUpdatedEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleDirectMessageReceivedEvent(DirectMessageReceivedEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleFeedLikeEvent(FeedLikeEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleNewFeedCommentEvent(NewFeedCommentEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleFolloweeFeedPostedEvent(FolloweeFeedPostedEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleRoleChangedEvent(RoleChangedEvent event) {
    sendToNotificationExchange(event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleWeatherAlertEvent(WeatherAlertEvent event) {
    sendToNotificationExchange(event);
  }

  private void sendToNotificationExchange(DomainEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchanges().notification(),
        rabbitMqProperties.routingKeys().notification(), event);
  }
}
