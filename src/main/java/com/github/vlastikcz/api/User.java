package com.github.vlastikcz.api;

import java.util.Collection;
import java.util.UUID;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.vlastikcz.core.serializer.LinkSerializer;

public class User {
    public static final String NOTIFICATION_GROUPS_LINK_REL = "notification-groups";

    private final UUID id;
    @JsonSerialize(using = LinkSerializer.class)
    private final Collection<Link> links;

    public User(final UUID id, final Collection<Link> links) {
        this.id = id;
        this.links = links;
    }

    public UUID getId() {
        return id;
    }

    public Collection<Link> getLinks() {
        return links;
    }
}
