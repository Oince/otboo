package com.codeit.weatherwear.domain.notification.event;

import com.codeit.weatherwear.domain.notification.dto.NotificationDto;
import com.codeit.weatherwear.global.event.DomainEvent;
import java.util.List;

public record MultipleNotificationCreatedEvent(
    List<NotificationDto> notificationDtos
) implements DomainEvent {

}
