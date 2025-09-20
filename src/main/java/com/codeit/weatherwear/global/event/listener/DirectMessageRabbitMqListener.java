package com.codeit.weatherwear.global.event.listener;

import com.codeit.weatherwear.domain.directmessage.dto.DirectMessageDto;
import com.codeit.weatherwear.global.event.dto.DirectMessageReceivedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
    value = @Queue(durable = "false", autoDelete = "true", exclusive = "true"),
    exchange = @Exchange(value = "${spring.rabbitmq.properties.exchanges.dm-fanout}",
        type = ExchangeTypes.FANOUT)
))
public class DirectMessageRabbitMqListener {

  private final SimpMessagingTemplate messagingTemplate;

  @Async("eventExecutor")
  @RabbitHandler
  public void handleDirectMessageReceivedEvent(DirectMessageReceivedEvent event) {
    DirectMessageDto dto = event.directMessageDto();
    String receiverId = dto.receiver().userId().toString();
    String senderId = dto.sender().userId().toString();

    String destination;

    if (receiverId.compareTo(senderId) < 0) {
      destination = String.format("/sub/direct-messages_%s_%s", receiverId, senderId);
    } else {
      destination = String.format("/sub/direct-messages_%s_%s", senderId, receiverId);
    }
    log.info("send direct message to {}. content={}", destination ,dto.content());
    messagingTemplate.convertAndSend(destination, dto);
  }
}
