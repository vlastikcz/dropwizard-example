package com.github.vlastikcz.core.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.api.Notification;
import com.github.vlastikcz.api.NotificationEventType;
import com.github.vlastikcz.api.NotificationGroup;
import com.github.vlastikcz.core.dto.NotificationDto;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;

public class NotificationGroupService {
    private final NotificationDtoRepository notificationDtoRepository;

    public NotificationGroupService(NotificationDtoRepository notificationDtoRepository) {
        this.notificationDtoRepository = Objects.requireNonNull(notificationDtoRepository, "'notificationDtoRepository' cannot be null");
    }

    public Collection<NotificationGroup> findByUserId(final UUID userId) {
        final Collection<NotificationDto> notificationDtos = notificationDtoRepository.findAllByUserId(userId);

        return convertAndGroupByEventType(notificationDtos).entrySet().stream()
                .map(e -> new NotificationGroup(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private Map<NotificationEventType, List<Notification>> convertAndGroupByEventType(final Collection<NotificationDto> notificationDtos) {
        return notificationDtos.stream()
                .collect(Collectors.groupingBy(
                        NotificationDto::getNotificationEventType,
                        Collectors.mapping(NotificationDto::toNotification, Collectors.toList())
                ));
    }
}
