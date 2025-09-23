package com.codeit.weatherwear.domain.follow.event;

import com.codeit.weatherwear.global.event.DomainEvent;
import java.util.UUID;

public record NewFollowerEvent(
    UUID receiverId,
    String followerName
) implements DomainEvent {

}
