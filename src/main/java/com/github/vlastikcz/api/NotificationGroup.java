package com.github.vlastikcz.api;

import java.util.Collection;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationGroup {
    private final NotificationEventType notificationEventType;
    private final Collection<Notification> notifications;

    @JsonCreator
    public NotificationGroup(final @JsonProperty("notificationEventType") NotificationEventType notificationEventType,
                             final @JsonProperty("notifications") Collection<Notification> notifications) {
        this.notificationEventType = notificationEventType;
        this.notifications = notifications;
    }

    public NotificationEventType getNotificationEventType() {
        return notificationEventType;
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    @Override
    public String toString() {
        return "NotificationGroup{" +
                "notificationEventType=" + notificationEventType +
                ", notifications=" + notifications +
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
        NotificationGroup that = (NotificationGroup) o;
        return notificationEventType == that.notificationEventType &&
                Objects.equals(notifications, that.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationEventType, notifications);
    }
}
