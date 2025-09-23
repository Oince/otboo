package com.codeit.weatherwear.domain.user.event;

import com.codeit.weatherwear.domain.user.entity.Role;
import com.codeit.weatherwear.global.event.DomainEvent;
import java.util.UUID;

public record RoleChangedEvent(
    UUID receiverId,
    Role newRoles,
    Role previousRoles
) implements DomainEvent {

}
