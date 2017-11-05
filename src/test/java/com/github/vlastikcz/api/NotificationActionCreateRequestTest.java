package com.github.vlastikcz.api;

import java.time.Instant;
import java.util.Optional;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class NotificationActionCreateRequestTest {
    private static final ObjectMapper objectMapper = Jackson.newObjectMapper();

    @Test
    public void requestIsDeserialized() throws Exception {
        final Instant timestamp = Instant.parse("2017-11-05T14:03:04.543Z");
        final NotificationActionCreateRequest expected = new NotificationActionCreateRequest(
                Optional.empty(), NotificationActionType.DELETE, Optional.of(timestamp)
        );
        assertEquals(expected,
                objectMapper.readValue(fixture("fixtures/notification-action-create-request.json"), NotificationActionCreateRequest.class));
    }
}