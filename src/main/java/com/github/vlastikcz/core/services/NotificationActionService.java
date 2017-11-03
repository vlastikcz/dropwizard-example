package com.github.vlastikcz.core.services;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.BadRequestException;

import com.github.vlastikcz.api.NotificationAction;
import com.github.vlastikcz.core.dto.NotificationActionDto;
import com.github.vlastikcz.core.repository.NotificationActionDtoRepository;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;

public class NotificationActionService {
    private final NotificationActionDtoRepository notificationActionDtoRepository;
    private final NotificationDtoRepository notificationDtoRepository;

    public NotificationActionService(final NotificationActionDtoRepository notificationActionDtoRepository,
                                     final NotificationDtoRepository notificationDtoRepository) {
        this.notificationActionDtoRepository = Objects.requireNonNull(notificationActionDtoRepository, "'notificationActionDtoRepository' cannot be null");
        this.notificationDtoRepository = Objects.requireNonNull(notificationDtoRepository, "'notificationDtoRepository' cannot be null");
    }

    public Collection<NotificationAction> findByNotificationId(final UUID notificationId) {
        return notificationActionDtoRepository.findByNotificationId(notificationId).stream()
                .map(NotificationActionDto::toNotificationAction)
                .collect(Collectors.toList());
    }

    public Optional<NotificationAction> findById(final UUID id) {
        return notificationActionDtoRepository.findById(id)
                .map(NotificationActionDto::toNotificationAction);
    }


    public NotificationAction create(NotificationAction notificationAction) {
        if (!notificationDtoRepository.exists(notificationAction.getNotificationId())) {
            throw new BadRequestException("unknown notification ID received: " + notificationAction.getNotificationId());
        }

        final NotificationActionDto notificationActionDto = NotificationActionDto.fromNotificationAction(notificationAction);
        return notificationActionDtoRepository.createIfNotExists(notificationActionDto).toNotificationAction();
    }

}
