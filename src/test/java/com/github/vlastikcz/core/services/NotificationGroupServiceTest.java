package com.github.vlastikcz.core.services;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.vlastikcz.api.NotificationActionType;
import com.github.vlastikcz.api.NotificationGroup;
import com.github.vlastikcz.core.dto.NotificationActionDto;
import com.github.vlastikcz.core.dto.NotificationDto;
import com.github.vlastikcz.core.repository.NotificationActionDtoRepository;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;

import static com.github.vlastikcz.api.NotificationEventSubType.CNN_COM;
import static com.github.vlastikcz.api.NotificationEventType.SITE_BLOCKED;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

public class NotificationGroupServiceTest {
    private NotificationGroupService notificationGroupService;
    private NotificationDtoRepository notificationDtoRepository;
    private NotificationActionDtoRepository notificationActionDtoRepository;

    @Before
    public void setup() {
        notificationDtoRepository = EasyMock.createMock(NotificationDtoRepository.class);
        notificationActionDtoRepository = EasyMock.createMock(NotificationActionDtoRepository.class);
        notificationGroupService = new NotificationGroupService(notificationDtoRepository, notificationActionDtoRepository);
    }

    @After
    public void verifyMocks() {
        EasyMock.verify(notificationDtoRepository, notificationActionDtoRepository);
    }

    public void replayMocks() {
        EasyMock.replay(notificationDtoRepository, notificationActionDtoRepository);
    }

    @Test
    public void findNotDeletedByUserIdDoesNotReturnDeletedNotifications() throws Exception {
        final UUID userId = UUID.fromString("59bfa5e1-1131-46ef-8b30-da4cbca7d576");

        final UUID deletedNotificationId = UUID.fromString("a63398c0-00cb-4a10-b6ec-05ebcec5f5ac");
        final Instant deletedNotificationTimestamp = Instant.parse("2017-11-05T11:59:59.599Z");
        final NotificationDto deletedNotificationDto = new NotificationDto(
                deletedNotificationId, userId, "title", "content", SITE_BLOCKED, CNN_COM, deletedNotificationTimestamp
        );
        final UUID markAsReadNotificationActionId = UUID.fromString("d1cd83e8-c857-4eb8-824b-ca08aeddf792");
        final Instant markAsReadNotificationActionTimestamp = Instant.parse("2017-11-05T12:55:59.599Z");
        final NotificationActionDto markAsReadNotificationAction = new NotificationActionDto(
                markAsReadNotificationActionId, deletedNotificationId, NotificationActionType.MARK_AS_READ, markAsReadNotificationActionTimestamp
        );

        final UUID deletedNotificationActionId = UUID.fromString("5a5e8602-3ee4-446d-87c8-8aae8aa93fcb");
        final Instant deletedNotificationActionTimestamp = Instant.parse("2017-11-05T12:59:59.599Z");
        final NotificationActionDto deletedNotificationAction = new NotificationActionDto(
                deletedNotificationActionId, deletedNotificationId, NotificationActionType.DELETE, deletedNotificationActionTimestamp
        );

        final UUID activeNotificationId = UUID.fromString("0f98133c-8726-4aa9-84f0-6cb6971b96d7");
        final Instant activeNotificationTimestamp = Instant.parse("2017-11-05T23:59:59.599Z");
        final NotificationDto activeNotificationDto = new NotificationDto(
                activeNotificationId, userId, "title", "content", SITE_BLOCKED, CNN_COM, activeNotificationTimestamp
        );
        final UUID activeNotificationActionId = UUID.fromString("2744d82e-3b12-40a2-83ff-9090ad9b15a1");
        final Instant activeNotificationActionTimestamp = Instant.parse("2017-11-05T13:59:59.599Z");
        final NotificationActionDto activeNotificationAction = new NotificationActionDto(
                activeNotificationActionId, activeNotificationId, NotificationActionType.MARK_AS_READ, activeNotificationActionTimestamp
        );

        expect(notificationDtoRepository.findAllByUserId(userId))
                .andReturn(asList(deletedNotificationDto, activeNotificationDto));
        expect(notificationActionDtoRepository.findByNotificationId(deletedNotificationId))
                .andReturn(asList(markAsReadNotificationAction, deletedNotificationAction));
        expect(notificationActionDtoRepository.findByNotificationId(activeNotificationId))
                .andReturn(asList(activeNotificationAction));
        replayMocks();
        final Collection<NotificationGroup> actual = notificationGroupService.findNotDeletedByUserId(userId);
        final Collection<NotificationGroup> expected = singletonList(
                new NotificationGroup(
                        activeNotificationDto.getNotificationEventType(),
                        singletonList(activeNotificationDto.toNotification())
                )
        );
        assertEquals(expected, actual);
    }

}