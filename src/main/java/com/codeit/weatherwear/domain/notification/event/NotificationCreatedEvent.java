package com.codeit.weatherwear.domain.notification.event;

import com.codeit.weatherwear.domain.notification.dto.NotificationDto;
import com.codeit.weatherwear.global.event.DomainEvent;

public record NotificationCreatedEvent(
    NotificationDto notificationDto
) implements DomainEvent {

}
