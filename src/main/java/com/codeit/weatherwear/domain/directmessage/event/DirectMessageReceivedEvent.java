package com.codeit.weatherwear.domain.directmessage.event;

import com.codeit.weatherwear.domain.directmessage.dto.DirectMessageDto;
import com.codeit.weatherwear.global.event.DomainEvent;

public record DirectMessageReceivedEvent(
    DirectMessageDto directMessageDto
) implements DomainEvent {

}
