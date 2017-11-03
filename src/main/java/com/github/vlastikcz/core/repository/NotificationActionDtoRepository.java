package com.github.vlastikcz.core.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.core.dto.NotificationActionDto;

import static java.util.Objects.requireNonNull;

public class NotificationActionDtoRepository {
    private final Map<UUID, NotificationActionDto> notificationActionDtos;

    public NotificationActionDtoRepository(final Map<UUID, NotificationActionDto> notificationActionDtos) {
        this.notificationActionDtos = requireNonNull(notificationActionDtos, "'notificationActionDtos' cannot be null");
    }

    public Collection<NotificationActionDto> findByNotificationId(final UUID notificationId) {
        return notificationActionDtos.values().stream()
                .filter(a -> a.getNotificationId().equals(notificationId))
                .collect(Collectors.toList());
    }

    public Optional<NotificationActionDto> findById(final UUID id) {
        return Optional.ofNullable(notificationActionDtos.get(id));
    }

    public NotificationActionDto createIfNotExists(final NotificationActionDto notificationActionDto) {
        requireNonNull(notificationActionDto, "'notificationActionDto' cannot be null");
        notificationActionDtos.put(notificationActionDto.getId(), notificationActionDto);
        return notificationActionDto;
    }




}
