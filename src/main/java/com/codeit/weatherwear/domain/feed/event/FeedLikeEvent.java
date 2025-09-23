package com.codeit.weatherwear.domain.feed.event;

import com.codeit.weatherwear.global.event.DomainEvent;
import java.util.UUID;

public record FeedLikeEvent(
    UUID receiverId,
    String likerName,
    String feedContent
) implements DomainEvent {

}
