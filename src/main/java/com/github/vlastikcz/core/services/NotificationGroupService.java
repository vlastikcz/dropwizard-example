package com.github.vlastikcz.core.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.api.Notification;
import com.github.vlastikcz.api.NotificationActionType;
import com.github.vlastikcz.api.NotificationEventType;
import com.github.vlastikcz.api.NotificationGroup;
import com.github.vlastikcz.core.dto.NotificationDto;
import com.github.vlastikcz.core.repository.NotificationActionDtoRepository;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;

import static java.util.Objects.requireNonNull;

public class NotificationGroupService {
    private final NotificationDtoRepository notificationDtoRepository;
    private final NotificationActionDtoRepository notificationActionDtoRepository;

    public NotificationGroupService(final NotificationDtoRepository notificationDtoRepository,
                                    final NotificationActionDtoRepository notificationActionDtoRepository) {
        this.notificationDtoRepository = requireNonNull(notificationDtoRepository, "'notificationDtoRepository' cannot be null");
        this.notificationActionDtoRepository = requireNonNull(notificationActionDtoRepository, "'notificationActionDtoRepository' cannot be null");
    }

    public Collection<NotificationGroup> findNotDeletedByUserId(final UUID userId) {
        final Collection<NotificationDto> notificationDtos = notificationDtoRepository.findAllByUserId(userId)
                .stream()
                .filter(n -> !isDeleted(n))
                .collect(Collectors.toList());

        return convertAndGroupByEventType(notificationDtos).entrySet().stream()
                .map(e -> new NotificationGroup(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private boolean isDeleted(NotificationDto notificationDto) {
        return notificationActionDtoRepository.findByNotificationId(notificationDto.getId()).stream()
                .anyMatch(a -> a.getNotificationActionType() == NotificationActionType.DELETE);
    }

    private Map<NotificationEventType, List<Notification>> convertAndGroupByEventType(final Collection<NotificationDto> notificationDtos) {
        return notificationDtos.stream()
                .collect(Collectors.groupingBy(
                        NotificationDto::getNotificationEventType,
                        Collectors.mapping(NotificationDto::toNotification, Collectors.toList())
                ));
    }
}
