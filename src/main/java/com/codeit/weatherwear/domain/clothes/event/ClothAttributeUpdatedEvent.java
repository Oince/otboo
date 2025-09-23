package com.codeit.weatherwear.domain.clothes.event;

import com.codeit.weatherwear.global.event.DomainEvent;

public record ClothAttributeUpdatedEvent(
    String attributeName
) implements DomainEvent {

}
