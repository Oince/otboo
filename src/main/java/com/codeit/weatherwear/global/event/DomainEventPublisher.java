package com.codeit.weatherwear.global.event;

public interface DomainEventPublisher {

  void publish(DomainEvent event);
}
