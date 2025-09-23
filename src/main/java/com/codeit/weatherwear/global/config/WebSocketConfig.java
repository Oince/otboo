package com.codeit.weatherwear.global.config;

import com.codeit.weatherwear.global.config.rabbitmq.RabbitMqConnection;
import com.codeit.weatherwear.domain.directmessage.interceptor.DirectMessageSubscriptionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompReactorNettyCodec;
import org.springframework.messaging.tcp.reactor.ReactorNettyTcpClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import reactor.netty.tcp.TcpClient;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final RabbitMqConnection rabbitMqConnection;
  private final DirectMessageSubscriptionInterceptor interceptor;

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {

    TcpClient tcpClient = TcpClient
        .create()
        .host(rabbitMqConnection.host())
        .port(61613);

    ReactorNettyTcpClient<byte[]> client = new ReactorNettyTcpClient<>(tcpClient,
        new StompReactorNettyCodec());

    registry
        .enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue")
        .setAutoStartup(true)
        .setTcpClient(client)
        .setRelayHost(rabbitMqConnection.host())
        .setRelayPort(61613)
        .setClientLogin(rabbitMqConnection.username())
        .setClientPasscode(rabbitMqConnection.password())
        .setSystemLogin(rabbitMqConnection.username())
        .setSystemPasscode(rabbitMqConnection.password());

    registry.setApplicationDestinationPrefixes("/pub");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry
        .addEndpoint("/ws")
        .setAllowedOriginPatterns("*")
        .withSockJS();
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(interceptor);
  }
}
