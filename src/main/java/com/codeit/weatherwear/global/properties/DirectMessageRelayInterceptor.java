package com.codeit.weatherwear.global.properties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DirectMessageRelayInterceptor implements ChannelInterceptor {

  private final RabbitMqProperties rabbitMqProperties;

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
      String destination = accessor.getDestination();

      if (destination != null && destination.startsWith("/sub/")) {
        String convertedDestination = convertDestination(destination);
        log.info("preSend: convertedDestination={}", convertedDestination);
        accessor.setDestination(convertedDestination);
      }
    }

    return message;
  }

  private String convertDestination(String originalDestination) {
    String dmExchange = rabbitMqProperties.exchanges().dm();
    return originalDestination.replace("/sub/", "/exchange/" + dmExchange + ".");
  }

}
