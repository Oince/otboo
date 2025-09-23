package com.codeit.weatherwear.domain.feed.event;

import com.codeit.weatherwear.global.event.DomainEvent;
import java.util.List;
import java.util.UUID;

public record FolloweeFeedPostedEvent(
    List<UUID> receiverIds,
    String followeeName,
    String content
) implements DomainEvent {

}
