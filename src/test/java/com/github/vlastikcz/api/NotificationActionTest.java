package com.github.vlastikcz.api;

import java.time.Instant;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.dropwizard.jackson.Jackson;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class NotificationActionTest {
    private static final ObjectMapper objectMapper = Jackson.newObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    @Test
    public void notificationActionIsProperlySerialized() throws Exception {
        final UUID id = UUID.fromString("1feca024-b353-45fe-a40a-e573916e4e0f");
        final UUID notificationId = UUID.fromString("ad0c2cb1-6e50-4764-99e0-5ee2e97d6728");
        final Instant timestamp = Instant.parse("2017-11-05T14:03:04.543Z");
        final NotificationAction notificationAction = new NotificationAction(
                id, notificationId, NotificationActionType.MARK_AS_READ, timestamp
        );
        final String expected = objectMapper.readValue(fixture("fixtures/notification-action.json"), JsonNode.class).toString();
        assertEquals(expected, objectMapper.writeValueAsString(notificationAction));
    }

}