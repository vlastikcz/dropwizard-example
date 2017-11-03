package com.github.vlastikcz.core.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.core.dto.NotificationDto;

public class NotificationDtoRepository {
    private final Map<UUID, NotificationDto> notificationDtos;

    public NotificationDtoRepository(final Map<UUID, NotificationDto> notificationDtos) {
        this.notificationDtos = notificationDtos;
    }

    public Optional<NotificationDto> findById(UUID id) {
        return Optional.ofNullable(notificationDtos.get(id));
    }

    public boolean exists(UUID id) {
        return notificationDtos.containsKey(id);
    }

    public List<NotificationDto> findAllByUserId(UUID userId) {
        return notificationDtos.values().stream()
                .filter(e -> e.getUserId().equals(userId))
                .sorted(sortNotifications())
                .collect(Collectors.toList());
    }

    public List<NotificationDto> findAll() {
        return notificationDtos.values().stream()
                .sorted(sortNotifications())
                .collect(Collectors.toList());
    }

    public NotificationDto createIfNotExists(final NotificationDto notificationDto) {
        Objects.requireNonNull(notificationDto, "'notificationDto' cannot be null");
        notificationDtos.put(notificationDto.getId(), notificationDto);
        return notificationDto;
    }

    private static Comparator<NotificationDto> sortNotifications() {
        return Comparator.nullsLast((left, right) -> right.getEventTimestamp().compareTo(left.getEventTimestamp()));
    }

}
