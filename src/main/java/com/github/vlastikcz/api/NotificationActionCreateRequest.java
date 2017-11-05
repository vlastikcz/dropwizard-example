package com.github.vlastikcz.api;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationActionCreateRequest {
    private final Optional<UUID> id;
    @NotNull
    private final NotificationActionType notificationActionType;
    private final Optional<Instant> notificationActionTimestamp;

    @JsonCreator
    public NotificationActionCreateRequest(final @JsonProperty("id") Optional<UUID> id,
                                           final @JsonProperty("notificationActionType") NotificationActionType notificationActionType,
                                           final @JsonProperty("notificationActionTimestamp") Optional<Instant> notificationActionTimestamp) {
        this.id = id;
        this.notificationActionType = notificationActionType;
        this.notificationActionTimestamp = notificationActionTimestamp;
    }

    public Optional<UUID> getId() {
        return id;
    }

    public NotificationActionType getNotificationActionType() {
        return notificationActionType;
    }

    public Optional<Instant> getNotificationActionTimestamp() {
        return notificationActionTimestamp;
    }

    @Override
    public String toString() {
        return "NotificationActionCreateRequest{" +
                "id=" + id +
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
        NotificationActionCreateRequest that = (NotificationActionCreateRequest) o;
        return Objects.equals(id, that.id) &&
                notificationActionType == that.notificationActionType &&
                Objects.equals(notificationActionTimestamp, that.notificationActionTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationActionType, notificationActionTimestamp);
    }
}
