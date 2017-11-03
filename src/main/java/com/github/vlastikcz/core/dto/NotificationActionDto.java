package com.github.vlastikcz.core.dto;

import java.time.Instant;
import java.util.UUID;

import com.github.vlastikcz.api.NotificationAction;
import com.github.vlastikcz.api.NotificationActionType;

public class NotificationActionDto {
    private final UUID id;
    private final UUID notificationId;
    private final NotificationActionType notificationActionType;
    private final Instant notificationActionTimestamp;

    public NotificationActionDto(UUID id, UUID notificationId, NotificationActionType notificationActionType, Instant notificationActionTimestamp) {
        this.id = id;
        this.notificationId = notificationId;
        this.notificationActionType = notificationActionType;
        this.notificationActionTimestamp = notificationActionTimestamp;
    }

    public NotificationAction toNotificationAction() {
        return new NotificationAction(id, notificationId, notificationActionType, notificationActionTimestamp);
    }

    public static NotificationActionDto fromNotificationAction(NotificationAction notificationAction) {
        return new NotificationActionDto(
                notificationAction.getId(),
                notificationAction.getNotificationId(),
                notificationAction.getNotificationActionType(),
                notificationAction.getNotificationActionTimestamp()
        );
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
