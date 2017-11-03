package com.github.vlastikcz.api;

import java.util.Collection;

public class NotificationGroup {
    private final NotificationEventType notificationEventType;
    private final Collection<Notification> notifications;

    public NotificationGroup(NotificationEventType notificationEventType, Collection<Notification> notifications) {
        this.notificationEventType = notificationEventType;
        this.notifications = notifications;
    }

    public NotificationEventType getNotificationEventType() {
        return notificationEventType;
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }
}
