package com.github.vlastikcz.resources;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.github.vlastikcz.api.Notification;
import com.github.vlastikcz.api.NotificationEventType;
import com.github.vlastikcz.api.NotificationGroup;
import com.github.vlastikcz.core.services.NotificationGroupService;

import io.dropwizard.testing.junit.ResourceTestRule;

import static java.util.Collections.emptyList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class UserNotificationGroupResourceTest {
    private static final NotificationGroupService notificationGroupService = EasyMock.createMock(NotificationGroupService.class);

    @ClassRule
    public static final ResourceTestRule resourceTestRule = ResourceTestRule.builder()
            .addResource(new UserNotificationGroupResource(notificationGroupService))
            .build();

    @Before
    public void setup() {
        EasyMock.reset(notificationGroupService);
    }

    @Test
    public void getGroupsReturnAllGroupsWithNotifications() {
        final UUID userId = UUID.fromString("fc6a2d21-b819-420b-a1b3-e7a18699b351");
        final UUID id = UUID.fromString("5e7826c3-7926-41bb-804b-9f30e1072645");
        final List<Link> links = Collections.singletonList(Link.fromUri("http://127.0.0.1/self-link").rel("self").build());
        final Notification notification = new Notification(
                id, userId, "title", "content", links
        );

        final NotificationGroup notificationGroup = new NotificationGroup(
                NotificationEventType.SITE_BLOCKED, Collections.singletonList(notification)
        );
        final List<NotificationGroup> expected = Collections.singletonList(notificationGroup);

        expect(notificationGroupService.findNotDeletedByUserId(userId)).andReturn(expected);
        replay(notificationGroupService);
        final List<NotificationGroup> actual = resourceTestRule.target("/user/" + userId + "/notification-group").request()
                .get(new GenericType<List<NotificationGroup>>() {
                });
        assertEquals(expected, actual);
        verify(notificationGroupService);
    }

    @Test
    public void getGroupsReturnsEmptyArrayIfNotificationsAreEmpty() {
        final UUID userId = UUID.fromString("fc6a2d21-b819-420b-a1b3-e7a18699b351");

        expect(notificationGroupService.findNotDeletedByUserId(userId)).andReturn(emptyList());
        replay(notificationGroupService);
        final List<NotificationGroup> actual = resourceTestRule.target("/user/" + userId + "/notification-group").request()
                .get(new GenericType<List<NotificationGroup>>() {
                });
        assertEquals(emptyList(), actual);
        verify(notificationGroupService);
    }

}