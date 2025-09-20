package com.codeit.weatherwear.global.event.publisher;

import com.codeit.weatherwear.global.event.dto.ClothAttributeAddedEvent;
import com.codeit.weatherwear.global.event.dto.ClothAttributeUpdatedEvent;
import com.codeit.weatherwear.global.event.dto.DirectMessageReceivedEvent;
import com.codeit.weatherwear.global.event.dto.FeedLikeEvent;
import com.codeit.weatherwear.global.event.dto.FolloweeFeedPostedEvent;
import com.codeit.weatherwear.global.event.dto.NewFeedCommentEvent;
import com.codeit.weatherwear.global.event.dto.NewFollowerEvent;
import com.codeit.weatherwear.global.event.dto.RoleChangedEvent;
import com.codeit.weatherwear.global.event.dto.WeatherAlertEvent;
import com.codeit.weatherwear.global.properties.RabbitMqProperties;
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
public class NotificationRabbitmqPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final RabbitMqProperties rabbitMqProperties;

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleNewFollowerEvent(NewFollowerEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleClothAttributeAddedEvent(ClothAttributeAddedEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleClothAttributeUpdatedEvent(ClothAttributeUpdatedEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleDirectMessageReceivedEvent(DirectMessageReceivedEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleFeedLikeEvent(FeedLikeEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleNewFeedCommentEvent(NewFeedCommentEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleFolloweeFeedPostedEvent(FolloweeFeedPostedEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleRoleChangedEvent(RoleChangedEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleWeatherAlertEvent(WeatherAlertEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchange(),
        rabbitMqProperties.queues().notifications(), event);
  }
}
