package com.github.vlastikcz.api;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.vlastikcz.core.serializer.LinkDeserializer;
import com.github.vlastikcz.core.serializer.LinkSerializer;

public class User {
    public static final String NOTIFICATION_GROUPS_LINK_REL = "notification-groups";

    private final UUID id;
    @JsonSerialize(contentUsing = LinkSerializer.class)
    @JsonDeserialize(contentUsing = LinkDeserializer.class)
    private final Collection<Link> links;

    @JsonCreator
    public User(final @JsonProperty("id") UUID id, final @JsonProperty("links") Collection<Link> links) {
        this.id = id;
        this.links = links;
    }

    public UUID getId() {
        return id;
    }

    public Collection<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", links=" + links +
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(links, user.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, links);
    }
}
