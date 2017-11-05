package com.github.vlastikcz.api;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Link;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class NotificationGroupTest {
    private static final ObjectMapper objectMapper = Jackson.newObjectMapper();

    @Test
    public void notificationGroupIsProperlySerialized() throws Exception {
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");
        final UUID userId = UUID.fromString("15750341-8f06-4829-ad77-2d3b47cae9fa");
        final List<Link> links = Collections.singletonList(Link.fromUri("http://127.0.0.1/self-link").rel("self").build());
        final Notification notification = new Notification(
                id, userId, "title", "content", links
        );

        final NotificationGroup notificationGroup = new NotificationGroup(
                NotificationEventType.SITE_BLOCKED, Collections.singletonList(notification)
        );
        final String expected = objectMapper.readValue(fixture("fixtures/notification-group.json"), JsonNode.class).toString();
        assertEquals(expected, objectMapper.writeValueAsString(notificationGroup));
    }

}