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

public class Notification {
    public static final String NOTIFICATION_ACTIONS_LINK_REL = "notification-actions";
    public static final String SELF_LINK_REL = "self";

    private final UUID id;
    private final UUID userId;
    private final String title;
    private final String content;
    @JsonSerialize(contentUsing = LinkSerializer.class)
    @JsonDeserialize(contentUsing = LinkDeserializer.class)
    private final Collection<Link> links;

    @JsonCreator
    public Notification(final @JsonProperty("id") UUID id,
                        final @JsonProperty("userId") UUID userId,
                        final @JsonProperty("title") String title,
                        final @JsonProperty("content") String content,
                        final @JsonProperty("links") Collection<Link> links) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.links = links;
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

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
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
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(links, that.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, content, links);
    }
}
