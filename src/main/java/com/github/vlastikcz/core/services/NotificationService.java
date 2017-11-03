package com.github.vlastikcz.core.services;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.api.Notification;
import com.github.vlastikcz.core.dto.NotificationDto;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;

public class NotificationService {
    private final NotificationDtoRepository notificationDtoRepository;

    public NotificationService(NotificationDtoRepository notificationDtoRepository) {
        this.notificationDtoRepository = notificationDtoRepository;
    }

    public Optional<Notification> findById(UUID id) {
        return notificationDtoRepository.findById(id)
                .map(NotificationDto::toNotification);
    }

    public Collection<Notification> findByUserId(UUID userId) {
        return notificationDtoRepository.findAllByUserId(userId).stream()
                .map(NotificationDto::toNotification)
                .collect(Collectors.toList());
    }
}
