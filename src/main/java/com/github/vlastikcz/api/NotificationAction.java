package com.github.vlastikcz.api;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationAction {
    private final UUID id;
    @NotNull
    private final UUID notificationId;
    @NotNull
    private final NotificationActionType notificationActionType;
    private final Instant notificationActionTimestamp;

    @JsonCreator
    public NotificationAction(final @JsonProperty("id") UUID id,
                              final @JsonProperty("notificationId") UUID notificationId,
                              final @JsonProperty("notificationActionType") NotificationActionType notificationActionType,
                              final @JsonProperty("notificationActionTimestamp") Instant notificationActionTimestamp) {
        this.id = id;
        this.notificationId = notificationId;
        this.notificationActionType = notificationActionType;
        this.notificationActionTimestamp = notificationActionTimestamp;
    }

    public UUID getId() {
        return id;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public NotificationActionType getNotificationActionType() {
        return notificationActionType;
    }

    public Instant getNotificationActionTimestamp() {
        return notificationActionTimestamp;
    }
}
