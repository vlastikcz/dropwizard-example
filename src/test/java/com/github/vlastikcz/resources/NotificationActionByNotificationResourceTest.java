package com.github.vlastikcz.resources;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.easymock.EasyMock;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.github.vlastikcz.api.NotificationAction;
import com.github.vlastikcz.api.NotificationActionCreateRequest;
import com.github.vlastikcz.api.NotificationActionType;
import com.github.vlastikcz.core.services.NotificationActionFactory;
import com.github.vlastikcz.core.services.NotificationActionService;

import io.dropwizard.testing.junit.ResourceTestRule;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class NotificationActionByNotificationResourceTest {
    private static final NotificationActionService notificationService = EasyMock.createMock(NotificationActionService.class);
    private static final NotificationActionFactory notificationActionFactory = EasyMock.createMock(NotificationActionFactory.class);

    @ClassRule
    public static final ResourceTestRule resourceTestRule = ResourceTestRule.builder()
            .addResource(new NotificationActionByNotificationResource(notificationService, notificationActionFactory))
            .build();

    @Before
    public void setup() {
        EasyMock.reset(notificationService, notificationActionFactory);
    }

    @Test
    public void getNotificationActionsByNotificationId() throws Exception {
        final UUID notificationId = UUID.fromString("191c1b2e-147e-4b1e-b81d-63b2797d2559");
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");
        final Instant timestamp = Instant.parse("2017-11-05T14:33:22.210Z");
        final NotificationAction notificationAction = new NotificationAction(
                id, notificationId, NotificationActionType.DELETE, timestamp
        );
        final List<NotificationAction> expected = Collections.singletonList(notificationAction);

        EasyMock.expect(notificationService.findByNotificationId(notificationId)).andReturn(expected);
        replay(notificationService, notificationActionFactory);
        final List<NotificationAction> actual = resourceTestRule.target("/notification/" + notificationId + "/notification-action").request()
                .get(new GenericType<List<NotificationAction>>() {
                });
        assertEquals(actual, expected);
        verify(notificationService, notificationActionFactory);
    }

    @Test
    public void createCreatesNewNotificationWithProvidedIdAndTimestamp() throws Exception {
        final UUID notificationId = UUID.fromString("191c1b2e-147e-4b1e-b81d-63b2797d2559");
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");
        final Instant timestamp = Instant.parse("2017-11-05T14:33:22.210Z");
        final NotificationActionCreateRequest notificationActionCreateRequest = new NotificationActionCreateRequest(
                Optional.of(id), NotificationActionType.DELETE, Optional.of(timestamp)
        );
        final NotificationAction notificationAction = new NotificationAction(
                id, notificationId, NotificationActionType.DELETE, timestamp
        );

        EasyMock.expect(notificationActionFactory.fromRequest(notificationId, notificationActionCreateRequest)).andReturn(notificationAction);
        EasyMock.expect(notificationService.create(notificationAction)).andReturn(notificationAction);
        replay(notificationService, notificationActionFactory);
        final Response actual = resourceTestRule.target("/notification/" + notificationId + "/notification-action").request()
                .post(Entity.entity(notificationActionCreateRequest, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.CREATED.getStatusCode(), actual.getStatus());
        assertEquals(UriBuilder.fromPath(NotificationActionResource.NOTIFICATION_ACTION_BY_ID_PATH).build(id).getPath(),
                actual.getLocation().getPath());
        verify(notificationService, notificationActionFactory);
    }

    @Test
    public void createCreatesNewNotificationWithoutIdAndTimestamp() throws Exception {
        final UUID notificationId = UUID.fromString("191c1b2e-147e-4b1e-b81d-63b2797d2559");
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");
        final Instant timestamp = Instant.parse("2017-11-05T14:33:22.210Z");
        final NotificationActionCreateRequest notificationActionCreateRequest = new NotificationActionCreateRequest(
                Optional.empty(), NotificationActionType.DELETE, Optional.empty()
        );
        final NotificationAction notificationAction = new NotificationAction(
                id, notificationId, NotificationActionType.DELETE, timestamp
        );

        EasyMock.expect(notificationActionFactory.fromRequest(notificationId, notificationActionCreateRequest)).andReturn(notificationAction);
        EasyMock.expect(notificationService.create(notificationAction)).andReturn(notificationAction);
        replay(notificationService, notificationActionFactory);
        final Response actual = resourceTestRule.target("/notification/" + notificationId + "/notification-action").request()
                .post(Entity.entity(notificationActionCreateRequest, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.CREATED.getStatusCode(), actual.getStatus());
        assertEquals(UriBuilder.fromPath(NotificationActionResource.NOTIFICATION_ACTION_BY_ID_PATH).build(id).getPath(),
                actual.getLocation().getPath());
        verify(notificationService, notificationActionFactory);
    }

    @Test
    public void createCannotBeCalledWithoutActionType() throws Exception {
        final UUID notificationId = UUID.fromString("191c1b2e-147e-4b1e-b81d-63b2797d2559");
        final NotificationActionCreateRequest notificationActionCreateRequest = new NotificationActionCreateRequest(
                Optional.empty(), null, Optional.empty()
        );

        replay(notificationService, notificationActionFactory);
        final Response actual = resourceTestRule.target("/notification/" + notificationId + "/notification-action").request()
                .post(Entity.entity(notificationActionCreateRequest, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY_422, actual.getStatus());
        verify(notificationService, notificationActionFactory);
    }

}