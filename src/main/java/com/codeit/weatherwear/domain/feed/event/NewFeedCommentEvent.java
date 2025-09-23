package com.codeit.weatherwear.domain.feed.event;

import com.codeit.weatherwear.global.event.DomainEvent;
import java.util.UUID;

public record NewFeedCommentEvent(
    UUID receiverId,
    String authorName,
    String commentContent
) implements DomainEvent {

}
