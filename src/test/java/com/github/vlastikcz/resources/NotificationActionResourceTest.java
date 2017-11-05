package com.github.vlastikcz.resources;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.github.vlastikcz.api.NotificationAction;
import com.github.vlastikcz.api.NotificationActionType;
import com.github.vlastikcz.core.services.NotificationActionService;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class NotificationActionResourceTest {
    private static final NotificationActionService notificationService = EasyMock.createMock(NotificationActionService.class);

    @ClassRule
    public static final ResourceTestRule resourceTestRule = ResourceTestRule.builder()
            .addResource(new NotificationActionResource(notificationService))
            .build();

    @Before
    public void setup() {
        EasyMock.reset(notificationService);
    }

    @Test
    public void getNotificationActionReturnNotification() throws Exception {
        final UUID notificationId = UUID.fromString("191c1b2e-147e-4b1e-b81d-63b2797d2559");
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");
        final Instant timestamp = Instant.parse("2017-11-05T14:33:22.210Z");
        final NotificationAction expected = new NotificationAction(
                id, notificationId, NotificationActionType.DELETE, timestamp
        );

        expect(notificationService.findById(id)).andReturn(Optional.of(expected));
        replay(notificationService);
        final NotificationAction actual = resourceTestRule.target("/notification-action/" + id).request().get(NotificationAction.class);
        assertEquals(expected, actual);
        verify(notificationService);
    }

    @Test
    public void getNotificationActionReturnsNotFoundForInvalidId() throws Exception {
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");

        expect(notificationService.findById(id)).andReturn(Optional.empty());
        replay(notificationService);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resourceTestRule.target("/notification-action/" + id).request().get().getStatus());
        verify(notificationService);
    }

}