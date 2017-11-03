package com.github.vlastikcz.core.services;

import java.util.Collection;
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

    public NotificationActionService(NotificationActionDtoRepository notificationActionDtoRepository,
                                     NotificationDtoRepository notificationDtoRepository) {
        this.notificationActionDtoRepository = notificationActionDtoRepository;
        this.notificationDtoRepository = notificationDtoRepository;
    }

    public Collection<NotificationAction> findByNotificationId(UUID notificationId) {
        return notificationActionDtoRepository.findByNotificationId(notificationId).stream()
                .map(NotificationActionDto::toNotificationAction)
                .collect(Collectors.toList());
    }

    public Optional<NotificationAction> findById(UUID id) {
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
