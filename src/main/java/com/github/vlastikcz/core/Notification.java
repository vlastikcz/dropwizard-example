package com.github.vlastikcz.core;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.vlastikcz.resources.NotificationActionResource;
import com.github.vlastikcz.resources.NotificationResource;

public class Notification {
    private final UUID id;
    private final UUID userId;
    private final String title;
    private final String content;
    private final NotificationEventType notificationEventType;
    private final NotificationEventSubType notificationEventSubType;
    private final Instant eventTimestamp;

    @InjectLinks({
            // TODO getNotificationById string; do not use reflection
            @InjectLink(resource = NotificationResource.class, method = "getNotificationById", bindings = @Binding(name = "notificationId", value = "${instance.id}"), rel = "self"),
            @InjectLink(resource = NotificationActionResource.class, bindings = @Binding(name = "notificationId", value = "${instance.id}"), rel =
                    "userNotificationAction")
    })
    @JsonSerialize(using = LinkSerializer.class)
    private List<Link> links;

    public Notification(UUID id, UUID userId, String title, String content, NotificationEventType notificationEventType, NotificationEventSubType
            notificationEventSubType, Instant eventTimestamp) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.notificationEventType = notificationEventType;
        this.notificationEventSubType = notificationEventSubType;
        this.eventTimestamp = eventTimestamp;
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

    public List<Link> getLinks() {
        return links;
    }
}

