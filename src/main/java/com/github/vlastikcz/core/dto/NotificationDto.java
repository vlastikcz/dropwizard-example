package com.github.vlastikcz.core.dto;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import javax.ws.rs.core.Link;

import com.github.vlastikcz.api.Notification;
import com.github.vlastikcz.api.NotificationEventSubType;
import com.github.vlastikcz.api.NotificationEventType;

import static com.github.vlastikcz.api.Notification.NOTIFICATION_ACTIONS_LINK_REL;
import static com.github.vlastikcz.api.Notification.SELF_LINK_REL;
import static com.github.vlastikcz.resources.NotificationActionByNotificationResource.NOTIFICATION_ACTION_BY_NOTIFICATION_PATH;
import static com.github.vlastikcz.resources.NotificationResource.NOTIFICATION_BY_ID_PATH;

public class NotificationDto {
    private final UUID id;
    private final UUID userId;
    private final String title;
    private final String content;
    private final NotificationEventType notificationEventType;
    private final NotificationEventSubType notificationEventSubType;
    private final Instant eventTimestamp;

    public NotificationDto(UUID id, UUID userId, String title, String content, NotificationEventType notificationEventType, NotificationEventSubType
            notificationEventSubType, Instant eventTimestamp) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.notificationEventType = notificationEventType;
        this.notificationEventSubType = notificationEventSubType;
        this.eventTimestamp = eventTimestamp;
    }

    public Notification toNotification() {
        final Link notificationActions = Link.fromPath(NOTIFICATION_ACTION_BY_NOTIFICATION_PATH).rel(NOTIFICATION_ACTIONS_LINK_REL).build(id);
        final Link self = Link.fromPath(NOTIFICATION_BY_ID_PATH).rel(SELF_LINK_REL).build(id);
        return new Notification(id, userId, title, content, Arrays.asList(self, notificationActions));
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public NotificationEventType getNotificationEventType() {
        return notificationEventType;
    }

    public NotificationEventSubType getNotificationEventSubType() {
        return notificationEventSubType;
    }

    public Instant getEventTimestamp() {
        return eventTimestamp;
    }
}

