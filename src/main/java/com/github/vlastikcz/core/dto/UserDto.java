package com.github.vlastikcz.core.dto;

import java.util.Collections;
import java.util.UUID;

import javax.ws.rs.core.Link;

import com.github.vlastikcz.api.User;

import static com.github.vlastikcz.api.User.NOTIFICATION_GROUPS_LINK_REL;
import static com.github.vlastikcz.resources.UserNotificationGroupResource.NOTIFICATION_BY_USER_PATH;

public class UserDto {
    private final UUID id;

    public UserDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public User toUser() {
        final Link notifications = Link.fromPath(NOTIFICATION_BY_USER_PATH).rel(NOTIFICATION_GROUPS_LINK_REL).build(id);
        return new User(id, Collections.singletonList(notifications));
    }
}
