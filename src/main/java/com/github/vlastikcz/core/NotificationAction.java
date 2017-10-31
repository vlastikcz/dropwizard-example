package com.github.vlastikcz.core;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationAction {
    private final UUID notificationId;
    private final UUID notificationActionId;
    private final NotificationActionType notificationActionType;
    private final Instant notificationActionTimestamp;

    @JsonCreator
    public NotificationAction(@JsonProperty("notificationId") UUID notificationId,
                              @JsonProperty("notificationActionType") NotificationActionType notificationActionType) {
        this(notificationId, UUID.randomUUID(), notificationActionType, Instant.now());
    }

    private NotificationAction(UUID notificationId,
                              UUID notificationActionId,
                              NotificationActionType notificationActionType,
                              Instant notificationActionTimestamp) {

        this.notificationId = notificationId;
        this.notificationActionId = notificationActionId;
        this.notificationActionType = notificationActionType;
        this.notificationActionTimestamp = notificationActionTimestamp;
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public UUID getNotificationActionId() {
        return notificationActionId;
    }

    public NotificationActionType getNotificationActionType() {
        return notificationActionType;
    }

    public Instant getNotificationActionTimestamp() {
        return notificationActionTimestamp;
    }
}
