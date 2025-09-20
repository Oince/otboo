package com.codeit.weatherwear.global.event.publisher;

import com.codeit.weatherwear.global.event.dto.DirectMessageReceivedEvent;
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
public class DirectMessageRabbitMqPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final RabbitMqProperties rabbitMqProperties;

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleDirectMessageReceivedEvent(DirectMessageReceivedEvent event) {
    rabbitTemplate.convertAndSend(rabbitMqProperties.exchanges().dmFanout(), "", event);
  }

}
