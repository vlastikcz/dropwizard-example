package com.github.vlastikcz.resources;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.github.vlastikcz.api.Notification;
import com.github.vlastikcz.core.services.NotificationService;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class NotificationResourceTest {
    private static final NotificationService notificationService = EasyMock.createMock(NotificationService.class);

    @ClassRule
    public static final ResourceTestRule resourceTestRule = ResourceTestRule.builder()
            .addResource(new NotificationResource(notificationService))
            .build();

    @Before
    public void setup() {
        EasyMock.reset(notificationService);
    }

    @Test
    public void getNotificationByIdReturnsNotification() {
        final UUID userId = UUID.fromString("191c1b2e-147e-4b1e-b81d-63b2797d2559");
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");
        final List<Link> links = Collections.singletonList(Link.fromUri("http://127.0.0.1/self-link").rel("self").build());
        final Notification expected = new Notification(
                id, userId, "title", "content", links
        );

        expect(notificationService.findById(id)).andReturn(Optional.of(expected));
        replay(notificationService);
        final Notification actual = resourceTestRule.target("/notification/" + id).request().get(Notification.class);
        assertEquals(expected, actual);
        verify(notificationService);
    }

    @Test
    public void getNotificationByUnknownIdReturnsNotFound() {
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");

        expect(notificationService.findById(id)).andReturn(Optional.empty());
        replay(notificationService);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resourceTestRule.target("/notification/" + id).request().get().getStatus());
        verify(notificationService);
    }

}