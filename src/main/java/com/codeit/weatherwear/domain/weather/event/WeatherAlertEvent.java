package com.codeit.weatherwear.domain.weather.event;

import com.codeit.weatherwear.global.event.DomainEvent;
import java.util.List;
import java.util.UUID;

public record WeatherAlertEvent(
    List<UUID> receiverIds,
    String address,
    String content
) implements DomainEvent {

}
