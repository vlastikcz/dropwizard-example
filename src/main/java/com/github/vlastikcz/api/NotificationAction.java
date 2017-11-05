package com.github.vlastikcz.api;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationAction {
    private final UUID id;
    private final UUID notificationId;
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

    @Override
    public String toString() {
        return "NotificationAction{" +
                "id=" + id +
                ", notificationId=" + notificationId +
                ", notificationActionType=" + notificationActionType +
                ", notificationActionTimestamp=" + notificationActionTimestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotificationAction that = (NotificationAction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(notificationId, that.notificationId) &&
                notificationActionType == that.notificationActionType &&
                Objects.equals(notificationActionTimestamp, that.notificationActionTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationId, notificationActionType, notificationActionTimestamp);
    }
}
