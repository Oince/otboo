package com.codeit.weatherwear.global.event.publisher;

import com.codeit.weatherwear.domain.directmessage.dto.DirectMessageDto;
import com.codeit.weatherwear.global.event.dto.DirectMessageReceivedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class DirectMessageRabbitMqPublisher {

  private final SimpMessagingTemplate messagingTemplate;

  @Async("eventExecutor")
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
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
