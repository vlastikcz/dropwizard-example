package com.github.vlastikcz.core.repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.core.dto.NotificationActionDto;

public class NotificationActionDtoRepository {
    private final Map<UUID, NotificationActionDto> notificationActionDtos;

    public NotificationActionDtoRepository(final Map<UUID, NotificationActionDto> notificationActionDtos) {
        this.notificationActionDtos = notificationActionDtos;
    }

    public Optional<NotificationActionDto> findById(UUID id) {
        return Optional.ofNullable(notificationActionDtos.get(id));
    }

    public List<NotificationActionDto> findByNotificationId(UUID notificationId) {
        return notificationActionDtos.values().stream()
                .filter(a -> a.getNotificationId().equals(notificationId))
                .collect(Collectors.toList());
    }

    public NotificationActionDto createIfNotExists(final NotificationActionDto notificationActionDto) {
        Objects.requireNonNull(notificationActionDto, "'notificationActionDto' cannot be null");
        final NotificationActionDto savedNotificationActionDto = generateIdIfRequired(generateTimestampIfRequired(notificationActionDto));
        notificationActionDtos.put(savedNotificationActionDto.getId(), savedNotificationActionDto);
        return notificationActionDto;
    }

    private static NotificationActionDto generateIdIfRequired(final NotificationActionDto notificationActionDto) {
        return notificationActionDto.getId() == null
                ? generateId(notificationActionDto)
                : notificationActionDto;
    }

    private static NotificationActionDto generateTimestampIfRequired(final NotificationActionDto notificationActionDto) {
        return notificationActionDto.getNotificationActionTimestamp() == null
                ? generateTimestamp(notificationActionDto)
                : notificationActionDto;
    }

    private static NotificationActionDto generateId(final NotificationActionDto notificationActionDto) {
        return new NotificationActionDto(
                UUID.randomUUID(),
                notificationActionDto.getNotificationId(),
                notificationActionDto.getNotificationActionType(),
                notificationActionDto.getNotificationActionTimestamp()
        );
    }

    private static NotificationActionDto generateTimestamp(final NotificationActionDto notificationActionDto) {
        return new NotificationActionDto(
                notificationActionDto.getId(),
                notificationActionDto.getNotificationId(),
                notificationActionDto.getNotificationActionType(),
                Instant.now()
        );
    }

}
