package com.github.vlastikcz.api;

import java.util.Collection;
import java.util.UUID;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.vlastikcz.core.serializer.LinkSerializer;

public class Notification {
    public static final String NOTIFICATION_ACTIONS_LINK_REL = "notification-actions";
    public static final String SELF_LINK_REL = "self";

    private final UUID id;
    private final UUID userId;
    private final String title;
    private final String content;
    @JsonSerialize(using = LinkSerializer.class)
    private final Collection<Link> links;

    public Notification(UUID id, UUID userId, String title, String content, Collection<Link> links) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.links = links;
    }

    public static String getNotificationActionsLinkRel() {
        return NOTIFICATION_ACTIONS_LINK_REL;
    }

    public static String getSelfLinkRel() {
        return SELF_LINK_REL;
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

    public Collection<Link> getLinks() {
        return links;
    }
}
