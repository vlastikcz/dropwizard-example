package com.github.vlastikcz.core.services;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.github.vlastikcz.api.Notification;
import com.github.vlastikcz.core.dto.NotificationDto;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;

public class NotificationService {
    private final NotificationDtoRepository notificationDtoRepository;

    public NotificationService(final NotificationDtoRepository notificationDtoRepository) {
        this.notificationDtoRepository = Objects.requireNonNull(notificationDtoRepository, "'notificationDtoRepository' cannot be null");
    }

    public Optional<Notification> findById(final UUID id) {
        return notificationDtoRepository.findById(id)
                .map(NotificationDto::toNotification);
    }

}
