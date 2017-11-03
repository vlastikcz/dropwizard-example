package com.github.vlastikcz.api;

import java.time.Instant;
import java.util.UUID;

public class NotificationAction {
    private final UUID id;
    private final UUID notificationId;
    private final NotificationActionType notificationActionType;
    private final Instant notificationActionTimestamp;

    public NotificationAction(final UUID id,
                              final UUID notificationId,
                              final NotificationActionType notificationActionType,
                              final Instant notificationActionTimestamp) {
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
