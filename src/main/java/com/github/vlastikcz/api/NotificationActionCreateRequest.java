package com.github.vlastikcz.api;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationActionCreateRequest {
    private final UUID id;
    @NotNull
    private final NotificationActionType notificationActionType;
    private final Instant notificationActionTimestamp;

    @JsonCreator
    public NotificationActionCreateRequest(final @JsonProperty("id") UUID id,
                                           final @JsonProperty("notificationActionType") NotificationActionType notificationActionType,
                                           final @JsonProperty("notificationActionTimestamp") Instant notificationActionTimestamp) {
        this.id = id;
        this.notificationActionType = notificationActionType;
        this.notificationActionTimestamp = notificationActionTimestamp;
    }

    public UUID getId() {
        return id;
    }

    public NotificationActionType getNotificationActionType() {
        return notificationActionType;
    }

    public Instant getNotificationActionTimestamp() {
        return notificationActionTimestamp;
    }
}
