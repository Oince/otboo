package com.codeit.weatherwear.global.event;

import com.codeit.weatherwear.global.event.dto.DomainEvent;

public interface DomainEventPublisher {

  void publish(DomainEvent event);
}
